/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.crud.rest.fixtures.autogen;

import org.seedstack.business.assembler.BaseAssembler;
import org.seedstack.crud.rest.fixtures.model.delete.Bug;

public class BugRepresentationAssembler extends BaseAssembler<Bug, BugRepresentation> {

  @Override
  public void mergeAggregateIntoDto(Bug bug, BugRepresentation bugRepresentation) {

    bugRepresentation.setName(bug.getId().getPetname());
    bugRepresentation.setDaysInHome(bug.getDaysInHome());

  }

  @Override
  public void mergeDtoIntoAggregate(BugRepresentation bugRepresentation, Bug bug) {
    bug.setDaysInHome(bugRepresentation.getDaysInHome());
  }
}
