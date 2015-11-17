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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
      String pw = member.getPw();
      member.setPw(transformToMD5(pw));
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

  private String transformToMD5(String pw) {
    try {
      MessageDigest m = MessageDigest.getInstance("MD5");
      m.update(pw.getBytes(), 0, pw.length());
      return new BigInteger(1, m.digest()).toString(16);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

  @GET
  @Path("/login/{user}/{pw}")
  public String login(@PathParam("user") String username, @PathParam("pw") String password) {
    Member member = memberService.retrieveByUsername(username);
    if (member != null) {
      String pwmd5 = this.transformToMD5(password);
      if ((member.getPw().compareTo(pwmd5) == 0) && (pwmd5 != null)) {
        return member.getToken();
      }
    }
    return null;
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
