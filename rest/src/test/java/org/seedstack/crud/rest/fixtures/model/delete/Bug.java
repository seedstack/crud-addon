package org.seedstack.crud.rest.fixtures.model.delete;

import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.crud.rest.fixtures.model.AnimalId;

public class Bug implements AggregateRoot<AnimalId> {

  private Long daysInHome;
  private AnimalId dogId;

  public Bug(AnimalId id) {
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
