/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.crud.rest;

import static io.restassured.RestAssured.when;

import javax.inject.Inject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.domain.Repository;
import org.seedstack.crud.rest.fixtures.model.animal.AnimalId;
import org.seedstack.crud.rest.fixtures.model.animal.Dog;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.SeedITRunner;
import org.seedstack.seed.undertow.LaunchWithUndertow;

@RunWith(SeedITRunner.class)
@LaunchWithUndertow
public class ImplicitDeleteResourceIT {
    @Configuration("web.runtime.baseUrl")
    private String url;

    @Inject
    private Repository<Dog, AnimalId> dogRepository;

    @Before
    public void setUp() {
        dogRepository.add(new Dog(new AnimalId("snoopy")));
    }

    @After
    public void tearDown() {
        dogRepository.clear();
    }

    @Test
    public void delete() {
        when()
                .delete(url + "dogs/pluto")
                .then()
                .statusCode(404);
        when()
                .get(url + "dogs/snoopy")
                .then()
                .statusCode(200);
        when()
                .delete(url + "dogs/snoopy")
                .then()
                .statusCode(204);
        when()
                .get(url + "dogs/snoopy")
                .then()
                .statusCode(404);
    }
}
