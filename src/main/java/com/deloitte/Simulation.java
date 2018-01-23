package com.deloitte;

import com.deloitte.gps.GPSRoute;
import com.deloitte.gps.GPSUnit;
import com.deloitte.gps.RandomGeo;
import com.deloitte.osm.RouteGenerator;
import com.deloitte.producers.GPSTask;
import com.deloitte.producers.GPSProducer;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 * Main class of the project. Used to start the simulation
 */
public class Simulation {

    private static final Logger LOGGER = Logger.getLogger(Simulation.class);
    private static final int MAX_HEART_BEAT_INTERVAL = 5000;

    private ExecutorService executorService;
    private int nbOfCars;
    private double speedRange;
    private double routeRange;
    private RouteGenerator routeGenerator;
    private GPSProducer producer;

    /**
     *  Number of vehicles instances
        Country code where routes are generated
        Speed range for generated vehicles on tracks
        Range or maximum distance that tracks should have
     */
    public Simulation(int nbOfCars, double speedRange, double routeRange) {
        this.executorService = Executors.newFixedThreadPool(nbOfCars);
        this.nbOfCars = nbOfCars;
        this.speedRange = speedRange;
        this.routeRange = routeRange;
        this.routeGenerator = new RouteGenerator(new RandomGeo());
        this.producer = new GPSProducer();
    }

    private GPSUnit[] buildGPSUnits() {
        GPSUnit[] gpsUnits = new GPSUnit[nbOfCars];
        GPSRoute[] gpsRoute = generateRoutes();
        for (int i = 0; i < nbOfCars; i++) {
          gpsUnits[i] = new GPSUnit(speedRange, gpsRoute[i]);
        }
        return gpsUnits;
    }

    private GPSRoute[] generateRoutes() {
        System.out.println("Generating random routes");
        GPSRoute[] gpsRoutes = new GPSRoute[nbOfCars];
        for (int i = 0; i < nbOfCars; i++) {
            gpsRoutes[i] = routeGenerator.getRoute(routeRange);
        }
        System.out.println("Routes DONE");
        return gpsRoutes;
    }

    public void start() {
        GPSUnit[] gpsUnits = buildGPSUnits();
        for (int i = 0; i < nbOfCars; i++) {
            executorService.submit(new GPSTask(gpsUnits[i], producer, MAX_HEART_BEAT_INTERVAL));
        }
        executorService.shutdown();
    }

    public void waitForCompletion() {
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            LOGGER.error("There was an error with the simulations ex: " + e.getMessage());
        }
    }

    public void shutdown() {
        producer.shutdown();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of cars: ");
        int nbOfCars = sc.nextInt();
        System.out.println("Enter speed range(km/h): ");
        double speedRange = sc.nextDouble();
        System.out.println("Enter route range(km): ");
        double routeRange = sc.nextDouble();

        while (true) {
            Simulation simulation = new Simulation(nbOfCars, speedRange, routeRange);
            simulation.start();
            simulation.waitForCompletion();
            simulation.shutdown();
        }
    }
}
