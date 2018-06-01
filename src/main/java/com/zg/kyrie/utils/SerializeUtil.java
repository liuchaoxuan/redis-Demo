package com.zg.kyrie.utils;

import java.io.*;

/**
 * Created by liuchaox on 5/31/2018.
 */
public class SerializeUtil {

    /**
     * Change the value to bytes for redis storage
     * @param value
     * @return
     * @throws IOException
     */
    public static byte[] serialize(Object value) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(value);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * change bytes from redis to object value
     * @param bytes
     * @return
     * @throws Exception
     */
    public static Object unSerialize(byte[] bytes) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        return objectInputStream.readObject();
    }
}
