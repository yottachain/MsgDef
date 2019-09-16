package com.ytfs.common.tracing;

import brave.Tracing;
import io.opentracing.Tracer;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

public class GlobalTracer {

    private static Tracer tracer = null;

    public static void init(String uri, String servicename) {
        if (tracer == null) {
            if (uri == null || uri.trim().isEmpty()) {
                return;
            }
            Sender sender = OkHttpSender.create(uri.trim());
            AsyncReporter spanReporter = AsyncReporter.create(sender);
            Tracing braveTracing = Tracing.newBuilder()
                    .localServiceName(servicename)
                    .spanReporter(spanReporter)
                    .build();
            tracer = BraveTracer.create(braveTracing);
        }

    }

    public static Tracer getTracer() {
        return tracer;
    }

}
