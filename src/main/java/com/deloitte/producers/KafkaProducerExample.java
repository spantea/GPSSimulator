package com.deloitte.producers;

import com.deloitte.data.GPSData;
import com.deloitte.data.GPSDataSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;

import java.util.Properties;

public class KafkaProducerExample {
    private final static String TOPIC = "gps-topic";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092,localhost:9093";

    private static Producer<Long, GPSData> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GPSDataSerializer.class.getName());
        return new KafkaProducer<>(props);
    }

    static void runProducer(final int sendMessageCount) throws Exception {
        final Producer<Long, GPSData> producer = createProducer();

        GPSData gpsData = new GPSData("Fake IMEI", "Fake IMSI");
        gpsData.setSpeed(72);
        gpsData.setLatitude(123.1);
        gpsData.setLongitude(123.1);


        try {
            for (long index = 0; index < sendMessageCount; index++) {
                final ProducerRecord<Long, GPSData> record = new ProducerRecord<>(TOPIC, System.currentTimeMillis(), gpsData);

                producer.send(record).get();

                System.out.printf("sent record(key=%s value=%s)\n ", record.key(), record.value());
            }
        } finally {
            producer.flush();
            producer.close();
        }
    }
}
