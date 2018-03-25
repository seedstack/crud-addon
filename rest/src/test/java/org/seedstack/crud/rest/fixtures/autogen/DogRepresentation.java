package org.seedstack.crud.rest.fixtures.autogen;

import org.seedstack.business.assembler.AggregateId;
import org.seedstack.business.assembler.DtoOf;
import org.seedstack.business.assembler.FactoryArgument;
import org.seedstack.crud.rest.RestCrud;
import org.seedstack.crud.rest.fixtures.model.AnimalId;
import org.seedstack.crud.rest.fixtures.model.create.Dog;

@DtoOf(Dog.class)
@RestCrud(value = "dogResource", create = true, read = false, update = false, delete = false)
public class DogRepresentation {

  private String name;
  private Long daysInHome;

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

  public void setName(String dogId) {
    this.name = dogId;
  }

}
