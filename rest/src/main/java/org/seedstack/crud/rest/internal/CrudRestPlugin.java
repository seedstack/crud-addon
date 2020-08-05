/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.crud.rest.internal;

import com.google.inject.AbstractModule;
import io.nuun.kernel.api.plugin.InitState;
import io.nuun.kernel.api.plugin.context.InitContext;
import io.nuun.kernel.api.plugin.request.ClasspathScanRequest;
import io.nuun.kernel.api.plugin.request.ClasspathScanRequestBuilder;
import org.seedstack.seed.core.internal.AbstractSeedPlugin;
import org.seedstack.seed.rest.spi.RestProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CrudRestPlugin extends AbstractSeedPlugin implements RestProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(CrudRestPlugin.class);
    private final Set<Class<?>> crudGeneratedResources = new HashSet<>();
    private final List<Class<?>> crudResources = new ArrayList<>();
    private final CrudResourceGenerator generator = new CrudResourceGenerator();

    @Override
    public Collection<ClasspathScanRequest> classpathScanRequests() {
        return new ClasspathScanRequestBuilder()
                .predicate(CrudResourcePredicate.INSTANCE)
                .build();
    }

    @Override
    public String name() {
        return "crud-rest";
    }

    @Override
    public Object nativeUnitModule() {
        return new AbstractModule() {
            @Override
            protected void configure() {
                install(new RestCrudModule(crudGeneratedResources));
            }
        };
    }

    @Override
    public Set<Class<?>> providers() {
        return Collections.emptySet();
    }

    @Override
    public Set<Class<?>> resources() {
        return crudGeneratedResources;
    }

    @Override
    protected InitState initialize(InitContext initContext) {
        crudResources.addAll(initContext.scannedTypesByPredicate().get(CrudResourcePredicate.INSTANCE));
        LOGGER.debug("Found {} annotated DTO to be exposed as CRUD resource(s)", crudResources.size());
        crudResources.stream().map(generator::generate).forEach(crudGeneratedResources::add);
        return InitState.INITIALIZED;
    }
}
