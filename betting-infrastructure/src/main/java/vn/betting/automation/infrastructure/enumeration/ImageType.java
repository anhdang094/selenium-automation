package vn.betting.automation.infrastructure.enumeration;

public enum ImageType {

  ROOT(0),
  COMMENT(1);

  private final int code;

  ImageType(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }


}
