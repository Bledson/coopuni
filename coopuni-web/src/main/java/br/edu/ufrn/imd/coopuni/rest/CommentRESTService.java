package br.edu.ufrn.imd.coopuni.rest;

import br.edu.ufrn.imd.coopuni.model.Comment;
import br.edu.ufrn.imd.coopuni.service.CommentService;
import br.edu.ufrn.imd.coopuni.service.MemberService;
import br.edu.ufrn.imd.coopuni.util.SecurityFilter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.util.*;
import java.util.logging.Logger;

@Path("/comment")
@RequestScoped
public class CommentRESTService extends SecurityFilter {
  @Inject
  private CommentService commentService;

  @Inject
  private Logger log;

  @Inject
  private MemberService memberService;

  @Inject
  private Validator validator;

  @GET
  @Path("/post/{id:[0-9][0-9]*}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Comment> listAllCommentsByPostId(@PathParam("id") long id) {
    return commentService.retrieveAllByPostId(id);
  }

  @GET
  @Path("/member/{id:[0-9][0-9]*}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Comment> listAllCommentsByUserId(@PathParam("id") long id) {
    return commentService.retrieveAllByUserId(id);
  }

  @GET
  @Path("/{id:[0-9][0-9]*}")
  @Produces(MediaType.APPLICATION_JSON)
  public Comment lookupCommentById(@PathParam("id") long id) {
    Comment comment = commentService.retrieve(id);
    if (comment == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    return comment;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createcomment(Comment comment, @Context HttpHeaders hHeaders) {
    Response.ResponseBuilder builder;

    try {
      if (this.isUserAllowed(hHeaders)) {
        this.validateComment(comment);
        commentService.create(comment);
      }

      builder = Response.ok();
    } catch (ConstraintViolationException ce) {
      builder = createViolationResponse(ce.getConstraintViolations());
    } catch (Exception e) {
      Map<String, String> responseObj = new HashMap<>();
      responseObj.put("Erro", e.getMessage());
      builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }

    return builder.build();
  }

  private void validateComment(Comment comment) throws ValidationException {
    Set<ConstraintViolation<Comment>> violations = validator.validate(comment);

    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
    }
  }

  private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
    log.fine("Validação completa. violações encontradas: " + violations.size());

    Map<String, String> responseObj = new HashMap<>();

    for (ConstraintViolation<?> violation : violations) {
      responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
    }

    return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
  }
}
