package com.ytfs.common.tracing;

import brave.Tracing;
import io.opentracing.Tracer;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.okhttp3.OkHttpSender;

public class GlobalTracer {

    private static Tracer tracer = null;

    public static void init(String uri, String servicename) {
        if (tracer == null) {
            try {
                if (uri == null || uri.trim().isEmpty()) {
                    return;
                }
                URL url=new URL(uri);
                Sender sender = OkHttpSender.create(uri.trim());
                AsyncReporter spanReporter = AsyncReporter.create(sender);
                Tracing braveTracing = Tracing.newBuilder()
                        .localServiceName(servicename)
                        .spanReporter(spanReporter)
                        .build();
                tracer = BraveTracer.create(braveTracing);
            } catch (MalformedURLException ex) {             
            }
        }

    }

    public static Tracer getTracer() {
        return tracer;
    }

}
