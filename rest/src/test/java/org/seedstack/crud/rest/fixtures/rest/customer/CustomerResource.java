/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.crud.rest.fixtures.rest.customer;

import org.seedstack.crud.rest.BaseCrudResource;
import org.seedstack.crud.rest.fixtures.model.customer.Customer;
import org.seedstack.crud.rest.fixtures.model.customer.CustomerId;

import javax.ws.rs.Path;

@Path("/customers")
public class CustomerResource
        extends BaseCrudResource<Customer, CustomerId, CustomerRepresentation> {
}
