package io.github.eggloop.common;

import java.io.Serializable;

@FunctionalInterface
public interface StringHashCode extends Serializable {

    String hash();
}
