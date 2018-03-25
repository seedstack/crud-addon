package org.seedstack.crud.rest.fixtures.model.read;

import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.crud.rest.fixtures.model.AnimalId;

public class Bird implements AggregateRoot<AnimalId> {

  private Long daysInHome;
  private AnimalId dogId;

  public Bird(AnimalId id) {
    this.dogId = id;
  }

  public Long getDaysInHome() {
    return daysInHome;
  }

  @Override
  public AnimalId getId() {
    return dogId;
  }

  public void setDaysInHome(Long daysInHome) {
    this.daysInHome = daysInHome;
  }

}
