package br.edu.ufrn.imd.coopuni.rest;

import br.edu.ufrn.imd.coopuni.model.Comment;
import br.edu.ufrn.imd.coopuni.service.CommentService;
import br.edu.ufrn.imd.coopuni.service.PostService;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@Path("/comment")
@RequestScoped
public class CommentRESTService extends SecurityFilter {
  @Inject
  private CommentService commentService;

  @Inject
  private Logger log;

  @Inject
  private PostService postService;

  @Inject
  private Validator validator;

  @POST
  @Path("/{id}:[0-9][0-9]*")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createcomment(Comment comment, @PathParam("id") long id, @Context HttpHeaders hHeaders) {
    Response.ResponseBuilder builder;

    try {
      this.validateComment(comment);

      if (this.isUserAllowed(hHeaders))
        commentService.register(comment, id);

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
