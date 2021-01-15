package vn.betting.automation.core.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.betting.automation.infrastructure.enumeration.ErrorMessage;
import vn.betting.automation.infrastructure.response.RestResponse;

public class AbstractController {

  protected static Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

  protected RestResponse buildSuccessResponse(Object data) {
    RestResponse response = new RestResponse();
    response.setReturncode(ErrorMessage.SUCCESS.getCode());
    response.setReturnmessage(ErrorMessage.SUCCESS.getMessage());
    response.setData(data);
    return response;
  }

  protected RestResponse buildInvalidResponse() {
    RestResponse response = new RestResponse();
    response.setReturncode(ErrorMessage.INVALID_PARAM.getCode());
    response.setReturnmessage(ErrorMessage.INVALID_PARAM.getMessage());
    return response;
  }

  protected RestResponse buildAccessFailResponse() {
    RestResponse response = new RestResponse();
    response.setReturncode(ErrorMessage.ACCESS_FAIL.getCode());
    response.setReturnmessage(ErrorMessage.ACCESS_FAIL.getMessage());
    return response;
  }

  protected RestResponse buildFailResponse() {
    RestResponse response = new RestResponse();
    response.setReturncode(ErrorMessage.FAIL.getCode());
    response.setReturnmessage(ErrorMessage.FAIL.getMessage());
    return response;
  }

  protected RestResponse buildNoContentResponse() {
    RestResponse response = new RestResponse();
    response.setReturncode(ErrorMessage.SUCCESS.getCode());
    response.setReturnmessage(ErrorMessage.SUCCESS.getMessage());
    response.setData(null);
    return response;
  }

}
