package com.deloitte.producers;

import com.deloitte.data.GPSData;
import com.deloitte.gps.GPSUnit;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Task used to simulate the gps unit moving, on a different thread
 */
public class GPSTask implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(GPSTask.class);
    private static final int MIN_SLEEP = 1000;

    private int max_sleep;
    private GPSUnit gpsUnit;
    private GPSProducer producer;
    private GPSData gpsData;

    public GPSTask(GPSUnit gpsUnit, GPSProducer producer, int timeIntervalMs) {
        this.gpsUnit = gpsUnit;
        this.producer = producer;
        this.max_sleep = timeIntervalMs;
        this.gpsData = new GPSData(gpsUnit.getIMEI(), gpsUnit.getIMSI());
    }

    public void run() {

        while (!gpsUnit.isAtDestination()) {

//            System.out.println(String.format("Latitude: " + gpsUnit.getCurrentPoint().getLatitude()
//                            + " Longitude: " + gpsUnit.getCurrentPoint().getLongitude()
//                            + " Speed: " + gpsUnit.getCurrentSpeed()
//                            + " IMEI: " +  gpsUnit.getIMEI()
//                            + " IMSI: " +  gpsUnit.getIMSI()));

            // send to kafka topic
            producer.send(getGpsData());
            gpsUnit.move();
            try {
                Thread.sleep(getSleepTime(MIN_SLEEP, max_sleep));
            } catch (InterruptedException e) {
                LOGGER.error("Error in gpsTask: " + e.getMessage());
            }
        }
        producer.send(getGpsData());
        System.out.println("DESTINATION !!!! " + gpsUnit.getIMEI());
    }

    private GPSData getGpsData() {
        gpsData.setDistance(gpsUnit.getDistance());
        gpsData.setLatitude(gpsUnit.getCurrentPoint().getLatitude());
        gpsData.setLongitude(gpsUnit.getCurrentPoint().getLongitude());
        gpsData.setSpeed(gpsUnit.getCurrentSpeed());
        return gpsData;
    }

    private int getSleepTime(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
