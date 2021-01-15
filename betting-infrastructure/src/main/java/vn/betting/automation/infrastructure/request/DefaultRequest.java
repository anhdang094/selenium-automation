package vn.betting.automation.infrastructure.request;

public class DefaultRequest {

  private int value;
  private String label;

  public DefaultRequest() {
  }

  public DefaultRequest(int value, String label) {
    this.value = value;
    this.label = label;
  }

  public DefaultRequest(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }
}
