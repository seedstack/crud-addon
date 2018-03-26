/*
 * Copyright © 2013-2018, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.crud.rest;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seedstack.business.domain.Repository;
import org.seedstack.crud.rest.fixtures.model.AnimalId;
import org.seedstack.crud.rest.fixtures.model.create.Dog;
import org.seedstack.crud.rest.fixtures.model.delete.Bug;
import org.seedstack.crud.rest.fixtures.model.read.Bird;
import org.seedstack.crud.rest.fixtures.model.update.Cat;
import org.seedstack.seed.Configuration;
import org.seedstack.seed.testing.junit4.SeedITRunner;
import org.seedstack.seed.undertow.LaunchWithUndertow;

@RunWith(SeedITRunner.class)
@LaunchWithUndertow
public class ImplicitResourceIT {

  @Configuration("web.runtime.baseUrl")
  private String url;

  @Inject
  private Repository<Cat, AnimalId> catRepository;
  @Inject
  private Repository<Dog, AnimalId> dogRepository;
  @Inject
  private Repository<Bug, AnimalId> bugRepository;
  @Inject
  private Repository<Bird, AnimalId> birdRepository;

  @Before
  public void setUp() {
    catRepository.add(new Cat(new AnimalId("felix")));
    bugRepository.add(new Bug(new AnimalId("pesky")));
    dogRepository.add(new Dog(new AnimalId("pluto")));
    birdRepository.add(new Bird(new AnimalId("tweety")));
    birdRepository.add(new Bird(new AnimalId("woodstock")));
    birdRepository.add(new Bird(new AnimalId("road runner")));

  }

  @After
  public void tearDown() {
    catRepository.clear();
    bugRepository.clear();
    dogRepository.clear();
    birdRepository.clear();
  }

  @Test
  public void attributePaginatedList() {
    when().get(url + "?attribute=id.firstName&value=Michael")
        .then()
        .statusCode(200)
        .body("size", equalTo(1))
        .body("items", hasSize(1));
  }

  @Test
  public void create() {

    long dogRepositorySize = this.dogRepository.size();

    given().body("{\"name\":\"goofy\"}")
        .contentType("application/json")
        .when()
        .post(url + "dogResource")
        .then()
        .statusCode(201)
        .header("Location", url + "dogResource/goofy");
    Assertions.assertThat(dogRepositorySize)
        .isLessThan(this.dogRepository.size());

  }

  @Test
  public void delete() {
    Assertions.assertThat(bugRepository.size()).isEqualTo(1L);
    String peskyUrl = url + "bugResource/pesky";
    when()
        .get(peskyUrl)
        .then()
        .statusCode(200);
    when()
        .delete(peskyUrl)
        .then()
        .statusCode(204);
    when()
        .get(peskyUrl)
        .then()
        .statusCode(404);
    Assertions.assertThat(bugRepository.size()).isEqualTo(0L);
  }

  @Test
  public void testDeleteInexistent() throws Exception {
    when()
        .delete(url + "bugResource/plankton")
        .then()
        .statusCode(404);
  }

  @Test
  public void get() {
    when().get(url + "Bird/tweety")
        .then()
        .statusCode(200)
        .body("name", equalTo("tweety"));
  }

  @Test
  public void list() {
    when().get(url + "Bird")
        .then()
        .statusCode(200)
        .body("[0].name", equalTo("road runner"))
        .body("[1].name", equalTo("tweety"))
        .body("[2].name", equalTo("woodstock"));

  }

  @Test
  public void offsetPaginatedList() {
    when().get(url + "?offset=1&limit=2")
        .then()
        .statusCode(200)
        .body("size", equalTo(2))
        .body("items", hasSize(2));
  }

  @Test
  public void pagePaginatedList() {
    when().get(url + "?page=1&limit=2")
        .then()
        .statusCode(200)
        .body("size", equalTo(2))
        .body("index", equalTo(1))
        .body("maxSize", equalTo(2))
        .body("totalSize", equalTo(3))
        .body("items", hasSize(2));
  }

  @Test
  public void update() {
    given().body("{\"firstName\":\"Robert\", \"lastName\":\"SMITH\", \"age\": 35}")
        .contentType("application/json")
        .when()
        .put(url + "/Robert SMITH")
        .then()
        .statusCode(200)
        .body("firstName", equalTo("Robert"))
        .body("lastName", equalTo("SMITH"))
        .body("age", equalTo(35));
  }
}
