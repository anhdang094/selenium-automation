package vn.betting.automation.infrastructure.enumeration;

public enum ErrorMessage {

  SUCCESS(1, "Thành công"),
  FAIL(-1, "Hệ thống đang có lỗi, vui lòng quay lại sau."),
  INVALID_PARAM(-2, "Vui lòng nhập đủ thông tin các trường yêu cầu"),
  ACCESS_FAIL(-3, "Vui lòng đăng nhập lại"),
  NO_CONTENT(-4, "Không tìm thấy nội dung bạn yêu cầu, vui lòng quay lại sau");

  private int code;
  private String message;

  ErrorMessage(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
