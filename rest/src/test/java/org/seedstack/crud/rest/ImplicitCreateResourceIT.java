/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.crud.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.domain.Repository;
import org.seedstack.crud.rest.fixtures.model.animal.AnimalId;
import org.seedstack.crud.rest.fixtures.model.animal.Bird;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.SeedITRunner;
import org.seedstack.seed.undertow.LaunchWithUndertow;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;

@RunWith(SeedITRunner.class)
@LaunchWithUndertow
public class ImplicitCreateResourceIT {
    @Configuration("runtime.web.baseUrl")
    private String baseUrl;

    @Inject
    private Repository<Bird, AnimalId> birdRepository;

    @Test
    public void testCreate() {
        given().body("{\"name\":\"ducky\"}")
                .contentType("application/json")
                .when()
                .post(baseUrl + "/birds")
                .then()
                .statusCode(201)
                .header("Location", baseUrl + "/birds/ducky");
    }

    @Test
    public void testDuplicate() throws Exception {
        given().body("{\"name\":\"ducky\"}")
                .contentType("application/json")
                .when()
                .post(baseUrl + "/birds")
                .then()
                .statusCode(201)
                .header("Location", baseUrl + "/birds/ducky");
        given().body("{\"name\":\"ducky\"}")
                .contentType("application/json")
                .when()
                .post(baseUrl + "/birds")
                .then()
                .statusCode(409);
    }

    @Before
    public void setUp() {
        birdRepository.add(new Bird(new AnimalId("tweety")));
    }

    @After
    public void tearDown() {
        birdRepository.clear();
    }
}
