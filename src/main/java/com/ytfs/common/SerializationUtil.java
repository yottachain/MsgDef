package com.ytfs.common;

import com.ytfs.service.packet.ErrorMessage;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

public class SerializationUtil {

    private static final ThreadLocal<LinkedBuffer> BUFFER_THREAD_LOCAL = ThreadLocal
            .withInitial(() -> LinkedBuffer.allocate(512));

    /**
     * 序列化对象
     *
     * @param obj
     * @return
     */
    public static byte[] serialize(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        if (obj instanceof ServiceException) {
            obj = ((ServiceException) obj).toErrMessage();
        }
        @SuppressWarnings("unchecked")
        Schema schema = RuntimeSchema.getSchema(obj.getClass());
        LinkedBuffer buffer = BUFFER_THREAD_LOCAL.get();
        try {
            byte[] protostuff = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
            byte[] bs = new byte[protostuff.length + 2];
            short id = MessageFactory.getMessageID(obj.getClass());
            Function.short2bytes(id, bs, 0);
            System.arraycopy(protostuff, 0, bs, 2, protostuff.length);
            return bs;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化
     *
     * @param paramArrayOfByte
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object deserialize(byte[] paramArrayOfByte) {
        if (paramArrayOfByte == null || paramArrayOfByte.length == 0) {
            throw new IllegalArgumentException();
        }
        short id = (short) Function.bytes2Integer(paramArrayOfByte, 0, 2);
        Class targetClass = MessageFactory.getMessageType(id);
        Object instance = null;
        try {
            instance = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Schema schema = RuntimeSchema.getSchema(targetClass);
        ProtostuffIOUtil.mergeFrom(paramArrayOfByte, 2, paramArrayOfByte.length - 2, instance, schema);
        if (instance instanceof ErrorMessage) {
            return new ServiceException((ErrorMessage) instance);
        }
        return instance;
    }

    /**
     * 序列化对象
     *
     * @param obj
     * @return
     */
    public static byte[] serializeNoID(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        @SuppressWarnings("unchecked")
        Schema schema = RuntimeSchema.getSchema(obj.getClass());
        LinkedBuffer buffer = BUFFER_THREAD_LOCAL.get();
        try {
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化
     *
     * @param <T>
     * @param paramArrayOfByte
     * @param targetClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T deserializeNoID(byte[] paramArrayOfByte, Class<T> targetClass) {
        if (paramArrayOfByte == null || paramArrayOfByte.length == 0) {
            throw new IllegalArgumentException();
        }
        T instance = null;
        try {
            instance = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Schema schema = RuntimeSchema.getSchema(targetClass);
        ProtostuffIOUtil.mergeFrom(paramArrayOfByte, instance, schema);
        return instance;
    }
}
