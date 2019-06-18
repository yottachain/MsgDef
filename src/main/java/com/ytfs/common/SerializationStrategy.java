package com.ytfs.common;

import io.protostuff.runtime.IdStrategy;

public interface SerializationStrategy {

    public IdStrategy getIdStrategy();
}
