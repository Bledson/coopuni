package br.edu.ufrn.imd.coopuni.rest;

import br.edu.ufrn.imd.coopuni.model.Comment;
import br.edu.ufrn.imd.coopuni.model.Post;
import br.edu.ufrn.imd.coopuni.service.CommentService;
import br.edu.ufrn.imd.coopuni.service.PostService;
import br.edu.ufrn.imd.coopuni.util.SecurityFilter;
import br.edu.ufrn.imd.coopuni.util.Validation;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by andreza on 01/11/15.
 */

@Path("/comment")
@RequestScoped
public class CommentRESTService extends SecurityFilter{

    @Inject
    private CommentService commentService;

    @Inject
    private PostService postService;

    @PermitAll
    @POST
    @Path("/{id}:[0-9][0-9]*")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createcomment(Comment comment, @PathParam("id") long id,@Context HttpHeaders hHeaders) {
        Response.ResponseBuilder builder = null;
        try {
//            this.validate(comment);
            if(this.isUserAllowed(hHeaders))
                commentService.register(comment,id);
        } catch (ConstraintViolationException ce) {
//            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (Exception e) {
            //
        }

        return builder.build();
    }

    @GET
    @Path("/teste")
    public String teste() {
        return "teste";
    }


}
