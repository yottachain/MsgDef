package com.ytfs.common;

import com.ytfs.service.packet.ErrorMessage;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.IdStrategy;
import io.protostuff.runtime.RuntimeSchema;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SerializationUtil {

    private static final ThreadLocal<LinkedBuffer> BUFFER_THREAD_LOCAL = ThreadLocal
            .withInitial(() -> LinkedBuffer.allocate(512));

    /**
     * 序列化对象
     *
     * @param obj
     * @return byte[]
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
     * @param paramArrayOfByte
     * @param instance
     */
    @SuppressWarnings("unchecked")
    public static void deserializeNoID(byte[] paramArrayOfByte, Object instance) {
        if (paramArrayOfByte == null || paramArrayOfByte.length == 0) {
            throw new IllegalArgumentException();
        }
        Class targetClass = instance.getClass();
        Schema schema = RuntimeSchema.getSchema(targetClass);
        ProtostuffIOUtil.mergeFrom(paramArrayOfByte, instance, schema);
    }

    /**
     * 序列化对象
     *
     * @param obj
     * @return byte[]
     */
    public static byte[] serialize(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException();
        }
        if (obj instanceof ServiceException) {
            obj = ((ServiceException) obj).toErrMessage();
        }
        IdStrategy idStrategy = null;
        if (obj instanceof SerializationStrategy) {
            idStrategy = ((SerializationStrategy) obj).getIdStrategy();
        }
        @SuppressWarnings("unchecked")
        Schema schema;
        if (idStrategy == null) {
            schema = RuntimeSchema.getSchema(obj.getClass());
        } else {
            schema = RuntimeSchema.createFrom(obj.getClass(), idStrategy);
        }
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
        IdStrategy idStrategy = null;
        if (instance instanceof SerializationStrategy) {
            idStrategy = ((SerializationStrategy) instance).getIdStrategy();
        }
        Schema schema;
        if (idStrategy == null) {
            schema = RuntimeSchema.getSchema(targetClass);
        } else {
            schema = RuntimeSchema.createFrom(targetClass, idStrategy);
        }
        ProtostuffIOUtil.mergeFrom(paramArrayOfByte, 2, paramArrayOfByte.length - 2, instance, schema);
        if (instance instanceof ErrorMessage) {
            return new ServiceException((ErrorMessage) instance);
        }
        return instance;
    }

    /**
     * 序列化对象
     *
     * @param <T>
     * @param map
     * @return
     */
    public static <T> byte[] serializeMap(Map<String, T> map) {
        if (map == null) {
            throw new IllegalArgumentException();
        }
        MapObject obj = new MapObject(map);
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
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> deserializeMap(byte[] paramArrayOfByte) {
        if (paramArrayOfByte == null || paramArrayOfByte.length == 0) {
            throw new IllegalArgumentException();
        }
        MapObject<T> instance = new MapObject();
        Schema schema = RuntimeSchema.getSchema(MapObject.class);
        ProtostuffIOUtil.mergeFrom(paramArrayOfByte, instance, schema);
        return instance.toMap();
    }

    public static class MapObject<T> {

        private List<String> keys;
        private List<T> values;

        public MapObject() {
        }

        public MapObject(Map<String, T> map) {
            keys = new ArrayList(map.keySet());
            values = new ArrayList(map.values());
        }

        /**
         * @return the keys
         */
        public List<String> getKeys() {
            return keys;
        }

        /**
         * @param keys the keys to set
         */
        public void setKeys(List<String> keys) {
            this.keys = keys;
        }

        /**
         * @return the values
         */
        public List<T> getValues() {
            return values;
        }

        /**
         * @param values the values to set
         */
        public void setValues(List<T> values) {
            this.values = values;
        }

        public void toMap(Map<String, T> map) {
            if (keys == null || values == null) {
                return;
            }
            int len = keys.size();
            for (int ii = 0; ii < len; ii++) {
                map.put(keys.get(ii), values.get(ii));
            }
        }

        public Map<String, T> toMap() {
            Map<String, T> map = new LinkedHashMap();
            if (keys == null || values == null) {
                return map;
            }
            int len = keys.size();
            for (int ii = 0; ii < len; ii++) {
                map.put(keys.get(ii), values.get(ii));
            }
            return map;
        }

    }
}
