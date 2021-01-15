package vn.betting.automation.infrastructure.response;

public class RestResponse extends DefaultResponse {

  private Object data;

  public RestResponse() {
  }

  public RestResponse(int returncode, String returnmessage) {
    this.returncode = returncode;
    this.returnmessage = returnmessage;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
