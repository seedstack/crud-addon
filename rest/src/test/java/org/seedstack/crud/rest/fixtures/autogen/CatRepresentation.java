package org.seedstack.crud.rest.fixtures.autogen;

import org.seedstack.business.assembler.AggregateId;
import org.seedstack.business.assembler.DtoOf;
import org.seedstack.business.assembler.FactoryArgument;
import org.seedstack.crud.rest.RestCrud;
import org.seedstack.crud.rest.fixtures.model.AnimalId;
import org.seedstack.crud.rest.fixtures.model.update.Cat;

@DtoOf(Cat.class)
@RestCrud(value = "catResource", create = false, read = false, update = true, delete = false)
public class CatRepresentation {

  private String name;
  private Long daysInHome;


  @AggregateId
  @FactoryArgument
  public AnimalId getId() {
    return new AnimalId(name);
  }

  public String getName() {
    return name;
  }

  public Long getDaysInHome() {
    return daysInHome;
  }

  public void setName(String catId) {
    this.name = catId;
  }

  public void setDaysInHome(Long daysInHome) {
    this.daysInHome = daysInHome;
  }

}
