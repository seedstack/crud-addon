/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.crud.rest.fixtures.autogen;

import org.seedstack.business.assembler.BaseAssembler;
import org.seedstack.crud.rest.fixtures.model.read.Bird;

public class BirdRepresentationAssembler extends BaseAssembler<Bird, BirdRepresentation> {

  @Override
  public void mergeAggregateIntoDto(Bird bird, BirdRepresentation birdRepresentation) {

    birdRepresentation.setName(bird.getId().getPetname());
    birdRepresentation.setDaysInHome(bird.getDaysInHome());

  }

  @Override
  public void mergeDtoIntoAggregate(BirdRepresentation birdRepresentation, Bird bird) {
    bird.setDaysInHome(birdRepresentation.getDaysInHome());
  }
}
