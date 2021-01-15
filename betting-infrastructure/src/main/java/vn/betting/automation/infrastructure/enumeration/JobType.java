package vn.betting.automation.infrastructure.enumeration;

public enum JobType {
  UNKNOW(0),
  SPRING_BATCH(1),
  SPARK(2);


  private final int type;

  JobType(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }
}
