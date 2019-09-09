package io.github.eggloop.expression.relational;

import io.github.eggloop.expression.arithmetic.Assignment;

import java.io.Serializable;
import java.util.function.Predicate;

public interface RelationalExpression extends Serializable {

    <T> DomainFunction<T> compile(Domain<T> domain);

    DomainFunction<String> print();

    DomainFunction<Predicate<Assignment>> logicalImplication();

}
