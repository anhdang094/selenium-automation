package vn.betting.automation.core.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class GenericEntity implements Serializable {

  protected static final long serialVersionUID = 8947981683527355663L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}