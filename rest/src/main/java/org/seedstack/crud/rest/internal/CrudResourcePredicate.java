/*
 * Copyright © 2013-2020, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.crud.rest.internal;

import org.seedstack.business.assembler.DtoOf;
import org.seedstack.crud.rest.RestCrud;
import org.seedstack.shed.reflect.AnnotationPredicates;
import org.seedstack.shed.reflect.ClassPredicates;

import java.lang.reflect.Modifier;
import java.util.function.Predicate;

/***
 * <p>This specification matches any class that is annotated with {@link RestCrud} and is a
 * {@link DtoOf}.</p>
 *
 * @author Sherpard2
 */
class CrudResourcePredicate implements Predicate<Class<?>> {

    static final CrudResourcePredicate INSTANCE = new CrudResourcePredicate();

    private CrudResourcePredicate() {
        // no instantiation allowed
    }

    @Override
    public boolean test(Class<?> candidate) {
        return AnnotationPredicates.classOrAncestorAnnotatedWith(RestCrud.class, false)
                .and(AnnotationPredicates.classOrAncestorAnnotatedWith(DtoOf.class, false))
                .and(ClassPredicates
                        .classModifierIs(Modifier.ABSTRACT).negate())
                .test(candidate);
    }
}
