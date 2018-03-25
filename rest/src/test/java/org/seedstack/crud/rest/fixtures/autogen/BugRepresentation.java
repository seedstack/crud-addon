package org.seedstack.crud.rest.fixtures.autogen;

import org.seedstack.business.assembler.AggregateId;
import org.seedstack.business.assembler.DtoOf;
import org.seedstack.business.assembler.FactoryArgument;
import org.seedstack.crud.rest.RestCrud;
import org.seedstack.crud.rest.fixtures.model.AnimalId;
import org.seedstack.crud.rest.fixtures.model.delete.Bug;

@DtoOf(Bug.class)
@RestCrud(value = "bugResource", create = false, read = true, update = true, delete = true)
public class BugRepresentation {

  private Long daysInHome;

  private String name;

  public Long getDaysInHome() {
    return daysInHome;
  }

  @AggregateId
  @FactoryArgument
  public AnimalId getId() {
    return new AnimalId(name);
  }

  public String getName() {
    return name;
  }

  public void setDaysInHome(Long daysInHome) {
    this.daysInHome = daysInHome;
  }

  public void setName(String name) {
    this.name = name;
  }

}
