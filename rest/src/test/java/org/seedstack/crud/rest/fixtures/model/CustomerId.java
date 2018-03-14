/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.crud.rest.fixtures.model;

import org.seedstack.business.domain.BaseValueObject;

public class CustomerId extends BaseValueObject {
    private final String firstName;
    private final String lastName;

    public CustomerId(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerId(String fullName) {
        String[] split = fullName.split(" ");
        this.firstName = split[0];
        this.lastName = split[1];
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
