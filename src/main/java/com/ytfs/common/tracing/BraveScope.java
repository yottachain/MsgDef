/*
 * Copyright 2016-2019 The OpenZipkin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ytfs.common.tracing;

import brave.Tracer.SpanInScope;
import io.opentracing.Scope;

/**
 * {@link BraveScope} is a simple {@link Scope} implementation that wraps the corresponding {@link
 * SpanInScope Brave scope}.
 *
 * @see SpanInScope
 */
public class BraveScope implements Scope {
  final SpanInScope delegate;

  /**
   * @param delegate a SpanInScope to be closed upon deactivation of this ActiveSpan
   */
  BraveScope(SpanInScope delegate) {
    this.delegate = delegate;
  }

  @Override public void close() {
    delegate.close();
  }

  /* @Override deprecated 0.32 method: Intentionally no override to ensure 0.33 works! */
  @Deprecated public BraveSpan span() {
    throw new UnsupportedOperationException("Not supported in OpenTracing 0.33+");
  }

  @Override public String toString() {
    return "BraveScope(" + delegate + ")";
  }
}
