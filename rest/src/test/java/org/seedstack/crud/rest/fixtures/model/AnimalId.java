package org.seedstack.crud.rest.fixtures.model;

import org.seedstack.business.domain.BaseValueObject;

public class AnimalId extends BaseValueObject {

  private static final long serialVersionUID = -5687053729759082639L;
  private String petname;

  public AnimalId(String petname) {
    this.petname = petname;
  }

  public String getPetname() {
    return petname;
  }

  public void setPetname(String petname) {
    this.petname = petname;
  }

  @Override
  public String toString() {
    return petname;
  }

}
