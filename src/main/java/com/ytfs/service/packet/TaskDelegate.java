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

public class TaskDelegate implements Delegate<P2PLocation> {

    private static final ThreadLocal<LinkedBuffer> BUFFER_THREAD_LOCAL = ThreadLocal
            .withInitial(() -> LinkedBuffer.allocate(512));

    @Override
    public WireFormat.FieldType getFieldType() {
        return WireFormat.FieldType.BYTES;
    }

    @Override
    public P2PLocation readFrom(Input input) throws IOException {
        P2PLocation location = new P2PLocation();
        Schema schema = RuntimeSchema.getSchema(P2PLocation.class);
        byte[] bs = input.readByteArray();
        ProtostuffIOUtil.mergeFrom(bs, 0, bs.length, location, schema);
        return location;
    }

    @Override
    public void writeTo(Output output, int i, P2PLocation v, boolean bln) throws IOException {
        LinkedBuffer buffer = BUFFER_THREAD_LOCAL.get();
        try {
            Schema schema = RuntimeSchema.getSchema(P2PLocation.class);
            byte[] res = ProtostuffIOUtil.toByteArray(v, schema, buffer);
            output.writeByteArray(i, res, bln);
        } finally {
            buffer.clear();
        }
    }

    @Override
    public void transfer(Pipe pipe, Input input, Output output, int i, boolean bln) throws IOException {
        output.writeByteArray(i, input.readByteArray(), bln);
    }

    @Override
    public Class<?> typeClass() {
        return P2PLocation.class;
    }

}
