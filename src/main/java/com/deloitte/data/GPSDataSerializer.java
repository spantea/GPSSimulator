package com.deloitte.data;

import org.apache.kafka.common.serialization.Serializer;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Kafka GPSData serializer
 */
public class GPSDataSerializer implements Serializer<GPSData> {

    private static final Logger LOGGER =  Logger.getLogger(GPSDataSerializer.class);

    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, GPSData gpsData) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out;
        byte[] gpsDataBytes = new byte[0];
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(gpsData);
            out.flush();
            gpsDataBytes = bos.toByteArray();
        } catch (IOException ex) {
            LOGGER.error("Error serializing GPSData object: " + ex.toString());
        } finally {
            try {
                bos.close();
            } catch (IOException ex) {
                // ignore close exception
            }
        }

        return gpsDataBytes;
    }

    @Override
    public void close() {

    }
}
