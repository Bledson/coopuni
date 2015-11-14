package br.edu.ufrn.imd.coopuni.rest;

import br.edu.ufrn.imd.coopuni.model.Member;
import br.edu.ufrn.imd.coopuni.service.MemberService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@Path("/members")
@RequestScoped
public class MemberRESTService {
  @Inject
  private Logger log;

  @Inject
  private MemberService memberService;

  @Inject
  private Validator validator;

  @GET
  @Path("/{id:[0-9][0-9]*}")
  @Produces(MediaType.APPLICATION_JSON)
  public Member lookupMemberById(@PathParam("id") long id) {
    Member member = memberService.retrieve(id);
    if (member == null) {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    return member;
  }

  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createMember(Member member) {
    Response.ResponseBuilder builder;

    try {
      validateMember(member);

      memberService.register(member);

      builder = Response.ok();
    } catch (ConstraintViolationException ce) {
      builder = createViolationResponse(ce.getConstraintViolations());
    } catch (ValidationException e) {
      Map<String, String> responseObj = new HashMap<>();
      responseObj.put("email", "E-mail já cadastrado");
      builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
    } catch (Exception e) {
      Map<String, String> responseObj = new HashMap<>();
      responseObj.put("Erro", e.getMessage());
      builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }

    return builder.build();
  }

  private void validateMember(Member member) throws ValidationException {
    Set<ConstraintViolation<Member>> violations = validator.validate(member);

    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
    }

    if (emailAlreadyExists(member.getEmail())) {
      throw new ValidationException("Violação de e-mail único");
    }

    if (usernameAlreadyExists(member.getUsername())) {
      throw new ValidationException("Violação de nome de usuário único");
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

  private boolean emailAlreadyExists(String email) {
    Member member = null;
    try {
      member = memberService.retrieveByEmail(email);
    } catch (NoResultException e) {
      // ignore
    }
    return member != null;
  }

  private boolean usernameAlreadyExists(String username) {
    Member member = null;
    try {
      member = memberService.retrieveByUsername(username);
    } catch (NoResultException e) {
      // ignore
    }
    return member != null;
  }
}
