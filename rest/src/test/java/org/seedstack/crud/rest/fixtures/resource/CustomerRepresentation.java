/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.crud.rest.fixtures.resource;

import org.seedstack.business.assembler.AggregateId;
import org.seedstack.business.assembler.DtoOf;
import org.seedstack.business.assembler.FactoryArgument;
import org.seedstack.crud.rest.fixtures.model.Customer;
import org.seedstack.crud.rest.fixtures.model.CustomerId;

@DtoOf(Customer.class)
public class CustomerRepresentation {
    private String firstName;
    private String lastName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @AggregateId
    @FactoryArgument
    public CustomerId getCustomerId() {
        return new CustomerId(firstName, lastName);
    }
}
