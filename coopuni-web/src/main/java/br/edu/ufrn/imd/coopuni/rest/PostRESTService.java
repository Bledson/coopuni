package br.edu.ufrn.imd.coopuni.rest;

import br.edu.ufrn.imd.coopuni.model.Post;
import br.edu.ufrn.imd.coopuni.service.*;
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

@Path("/posts")
@RequestScoped
public class PostRESTService extends SecurityFilter {
  @Inject
  private Logger log;

  @Inject
  private PostService postService;

  @Inject
  private Validator validator;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Post> listAllPosts() {
    return postService.retrieveAllOrderedByNewer();
  }

  @GET
  @Path("/member/{id:[0-9][0-9]*}")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Post> listAllPostsByUserId(@PathParam("id") long id) {
    return postService.retrieveAllByUserId(id);
  }

  @GET
  @Path("/{id:[0-9][0-9]*}")
  @Produces(MediaType.APPLICATION_JSON)
  public Post lookupPostById(@PathParam("id") long id) {
    Post post = postService.retrieve(id);
    if (post == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    return post;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createPost(Post post, @Context HttpHeaders hHeaders) {
    Response.ResponseBuilder builder;

    try {
      if (this.isUserAllowed(hHeaders)) {
        validatePost(post);
        postService.create(post);
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

  private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
    log.fine("Validação completa. violações encontradas: " + violations.size());

    Map<String, String> responseObj = new HashMap<>();

    for (ConstraintViolation<?> violation : violations) {
      responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
    }

    return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
  }

  private void validatePost(Post post) throws ValidationException {
    Set<ConstraintViolation<Post>> violations = validator.validate(post);

    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
    }
  }
}
