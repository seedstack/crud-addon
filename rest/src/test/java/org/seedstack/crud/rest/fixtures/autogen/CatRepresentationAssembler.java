/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.crud.rest.fixtures.autogen;

import org.seedstack.business.assembler.BaseAssembler;
import org.seedstack.crud.rest.fixtures.model.update.Cat;

public class CatRepresentationAssembler extends BaseAssembler<Cat, CatRepresentation> {

  @Override
  public void mergeAggregateIntoDto(Cat cat, CatRepresentation catRepresentation) {

    catRepresentation.setName(cat.getId().getPetname());
    catRepresentation.setDaysInHome(cat.getDaysInHome());

  }

  @Override
  public void mergeDtoIntoAggregate(CatRepresentation catRepresentation, Cat cat) {
    cat.setDaysInHome(catRepresentation.getDaysInHome());
  }
}
