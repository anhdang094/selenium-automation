package vn.betting.automation.infrastructure.dto;

public class GenericDto {

  private String id;

  public GenericDto() {
  }

  public GenericDto(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
