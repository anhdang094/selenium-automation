package vn.betting.automation.infrastructure.dto;

import java.util.List;

public class Page<T> {

  private long totalPages;
  private long totalElements;
  private long numberOfElements;
  private List<T> content;

  public long getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(long totalPages) {
    this.totalPages = totalPages;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  public long getNumberOfElements() {
    return numberOfElements;
  }

  public void setNumberOfElements(long numberOfElements) {
    this.numberOfElements = numberOfElements;
  }

  public List<T> getContent() {
    return content;
  }

  public void setContent(List<T> content) {
    this.content = content;
  }
}
