package br.edu.ufrn.imd.coopuni.util;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by andreza on 01/11/15.
 */

public class Validation<E> {

  @Inject
  private Validator validator;

  @Inject
  private Logger log;

  public Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
    log.fine("Validação completa. violações encontradas: " + violations.size());

    Map<String, String> responseObj = new HashMap<String, String>();

    for (ConstraintViolation<?> violation : violations) {
      responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
    }

    return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
  }

  public void validate(E type) throws ValidationException {
    Set<ConstraintViolation<E>> violations = validator.validate(type);

    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
    }
  }
}
