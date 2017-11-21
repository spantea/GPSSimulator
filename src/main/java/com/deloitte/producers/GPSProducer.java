package com.deloitte.producers;

import com.deloitte.data.GPSData;
import com.deloitte.data.GPSDataSerializer;
import org.apache.kafka.clients.producer.*;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.log4j.Logger;

/**
 * Kafka producer used to send gps data to Routing module
 */
public class GPSProducer {
    private static final Logger LOGGER =  Logger.getLogger(GPSProducer.class);
    private final static String TOPIC = "gps-topic";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092,localhost:9093";

    private KafkaProducer<Long, GPSData> producer;

    public GPSProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GPSDataSerializer.class.getName());

        this.producer = new KafkaProducer<>(props);
    }

    public void send(final GPSData gpsData)  {
        final ProducerRecord<Long, GPSData> record = new ProducerRecord<>(TOPIC, System.currentTimeMillis(), gpsData);

        try {
            producer.send(record).get();
        } catch (InterruptedException e) {
            LOGGER.error("Producer interrupted ex: " + e.getStackTrace());
        } catch (ExecutionException e) {
            LOGGER.error("Producer execution ex: " + e.getStackTrace());
        }

        System.out.printf("sent record(key=%s value=%s)\n", record.key(), record.value());
    }

    public void shutdown() {
        producer.flush();
        producer.close();
    }
}
