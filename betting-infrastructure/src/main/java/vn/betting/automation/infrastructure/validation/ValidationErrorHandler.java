package vn.betting.automation.infrastructure.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import vn.betting.automation.infrastructure.enumeration.ErrorMessage;
import vn.betting.automation.infrastructure.response.DefaultResponse;
import vn.betting.automation.infrastructure.exception.UnexpectedException;

@ControllerAdvice
public class ValidationErrorHandler {

  private static Logger LOGGER = LoggerFactory.getLogger(ValidationErrorHandler.class);

  private MessageSource messageSource;

  @Autowired
  public ValidationErrorHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * in case validation exception , we wil return Invalid param
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<?> processValidationError(MethodArgumentNotValidException ex) {
    LOGGER.error("Exception" + ex.getBindingResult().getModel());
    DefaultResponse response = new DefaultResponse();
    response.setReturncode(ErrorMessage.INVALID_PARAM.getCode());
    response.setReturnmessage(ErrorMessage.INVALID_PARAM.getMessage());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @ExceptionHandler({UnexpectedException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResponseEntity<?> processSystemError(UnexpectedException ex) {
    LOGGER.error("Exception" + ex.getMessage());
    DefaultResponse response = new DefaultResponse();
    response.setReturncode(ErrorMessage.FAIL.getCode());
    response.setReturnmessage(ErrorMessage.FAIL.getMessage());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
