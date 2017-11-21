package com.deloitte.data;

import com.deloitte.data.GPSData;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * Kafka GPSData deserializer
 */
public class GPSDataDeserializer implements Deserializer<GPSData>{
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public GPSData deserialize(String s, byte[] bytes) {

        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return (GPSData)is.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {

    }
}
