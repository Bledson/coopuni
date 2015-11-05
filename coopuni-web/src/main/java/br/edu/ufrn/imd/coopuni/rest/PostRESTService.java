package br.edu.ufrn.imd.coopuni.rest;

import br.edu.ufrn.imd.coopuni.boundary.GeolocationDAO;
import br.edu.ufrn.imd.coopuni.boundary.PostDAO;
import br.edu.ufrn.imd.coopuni.model.Area;
import br.edu.ufrn.imd.coopuni.model.Category;
import br.edu.ufrn.imd.coopuni.model.Geolocation;
import br.edu.ufrn.imd.coopuni.model.Post;
import br.edu.ufrn.imd.coopuni.service.AreaService;
import br.edu.ufrn.imd.coopuni.service.CategoryService;
import br.edu.ufrn.imd.coopuni.service.GeolocationService;
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


@Path("/posts")
@RequestScoped
public class PostRESTService extends SecurityFilter{
  @Inject
  private Logger log;

  @Inject
  AreaService areaService;

  @Inject
  private Validator validator;

  @Inject
  private CategoryService categoryService;

  @Inject
  GeolocationService geolocationService;

  @Inject
  private PostService postService;

  @GET
  @Path("/{id}:[0-9][0-9]*")
  @Produces(MediaType.APPLICATION_JSON)
  public Post lookupPostById(@PathParam("id") long id) {
    Post post = postService.get(id);
    if (post == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    return post;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createPost(Post post,@Context HttpHeaders hHeaders) {
    Response.ResponseBuilder builder = null;
    if (this.isUserAllowed(hHeaders)) {
      try {
        validatePost(post);
        Category category = categoryService.find(post.getCategory().getId());
        post.setCategory(category);
        Area area = areaService.retrieve(post.getArea().getId());
        post.setArea(area);
        geolocationService.register(post.getGeolocation().getLatitude(),post.getGeolocation().getLongitude(),area);
        postService.register(post);

        builder = Response.ok();
      } catch (ConstraintViolationException ce) {
        builder = createViolationResponse(ce.getConstraintViolations());
      } catch (Exception e) {
        //
      }
    }

      return builder.build();
  }

  private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
    log.fine("Validação completa. violações encontradas: " + violations.size());

    Map<String, String> responseObj = new HashMap<String, String>();

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
