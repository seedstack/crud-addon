package org.seedstack.crud.rest.fixtures.autogen;

import org.seedstack.business.assembler.AggregateId;
import org.seedstack.business.assembler.DtoOf;
import org.seedstack.business.assembler.FactoryArgument;
import org.seedstack.crud.rest.RestCrud;
import org.seedstack.crud.rest.fixtures.model.AnimalId;
import org.seedstack.crud.rest.fixtures.model.read.Bird;

@DtoOf(Bird.class)
@RestCrud(create = false, read = true, update = true, delete = false)
public class BirdRepresentation {

  private String name;

  public String getName() {
    return name;
  }

  private Long daysInHome;

  @AggregateId
  @FactoryArgument
  public AnimalId getId() {
    return new AnimalId(name);
  }

  public Long getDaysInHome() {
    return daysInHome;
  }

  public void setName(String birdId) {
    this.name = birdId;
  }

  public void setDaysInHome(Long daysInHome) {
    this.daysInHome = daysInHome;
  }

}
