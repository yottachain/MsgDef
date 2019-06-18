package com.ytfs.service.packet;

import io.protostuff.Input;
import io.protostuff.LinkedBuffer;
import io.protostuff.Output;
import io.protostuff.Pipe;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.WireFormat;
import io.protostuff.runtime.Delegate;
import io.protostuff.runtime.RuntimeSchema;
import java.io.IOException;

public class SpotCheckTaskDelegate implements Delegate<SpotCheckTask> {

    private static final ThreadLocal<LinkedBuffer> BUFFER_THREAD_LOCAL = ThreadLocal
            .withInitial(() -> LinkedBuffer.allocate(512));

    @Override
    public WireFormat.FieldType getFieldType() {
        return WireFormat.FieldType.BYTES;
    }

    @Override
    public SpotCheckTask readFrom(Input input) throws IOException {
        SpotCheckTask location = new SpotCheckTask();
        Schema schema = RuntimeSchema.getSchema(SpotCheckTask.class);
        byte[] bs = input.readByteArray();
        ProtostuffIOUtil.mergeFrom(bs, 0, bs.length, location, schema);
        return location;
    }

    @Override
    public void writeTo(Output output, int number, SpotCheckTask value, boolean repeated) throws IOException {
        LinkedBuffer buffer = BUFFER_THREAD_LOCAL.get();
        try {
            Schema schema = RuntimeSchema.getSchema(SpotCheckTask.class);
            byte[] res = ProtostuffIOUtil.toByteArray(value, schema, buffer);
            output.writeByteArray(number, res, repeated);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public void transfer(Pipe pipe, Input input, Output output, int number, boolean repeated) throws IOException {
        output.writeByteArray(number, input.readByteArray(), repeated);
    }

    @Override
    public Class<?> typeClass() {
        return SpotCheckTask.class;
    }
}
