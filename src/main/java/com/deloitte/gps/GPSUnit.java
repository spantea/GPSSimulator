package com.deloitte.gps;

import com.deloitte.osm.RouteGenerator;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.EarthCalc;
import com.grum.geocalc.Point;
import java.util.Random;

/**
 * Class that simulates moving vehicle/gps device
 * Requires maxSpeed and a GPSRoute for initialization
 */
public class GPSUnit {

    private static final Random RAND = new Random(1);

    private String IMEI;
    private String IMSI;
    private long timestamp;
    private double maxSpeed;
    private double currentSpeed;
    private int nextWayPointIndex;
    private GPSRoute route;
    private Point currentPoint;
    private boolean isAtDestination;


    public GPSUnit(double maxSpeed, GPSRoute route) {
        this.IMEI = GPSUtils.generateIMEI();
        this.IMSI = "123456789000000";
        this.timestamp = System.nanoTime();
        this.maxSpeed = maxSpeed / 3.6; // convert to m/s;
        this.route = route;
        this.nextWayPointIndex = 1;
        this.currentPoint = new Point(
                new DegreeCoordinate(route.getWaypoints().get(0).getLatitude()),
                new DegreeCoordinate(route.getWaypoints().get(0).getLongitude()));
        this.isAtDestination = false;
    }

    public String getIMEI() {
        return IMEI;
    }

    public String getIMSI() {
        return IMSI;
    }

    public double getCurrentSpeed() {
        return currentSpeed * 3.6;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public double getDistance() {
        return EarthCalc.getHarvesineDistance(currentPoint, route.getWaypoints().get(0));
    }

    public boolean isAtDestination() {
        return isAtDestination;
    }

    public void move() {

        if (!isAtDestination) {

            double travelDistance = getTravelDistance();
            double distanceToWayPoint = distanceToNextWayPoint();

            int nbOfWayPoints = route.getWaypoints().size();

            // if the travel distance is larger than the distance to the next way point and we did not reach the dest
            while (travelDistance > distanceToWayPoint && nextWayPointIndex < nbOfWayPoints) {
                travelDistance -= distanceToWayPoint;
                currentPoint = route.getWaypoints().get(nextWayPointIndex);
                nextWayPointIndex += 1;

                distanceToWayPoint = distanceToNextWayPoint();
            }

            // if we reached the last wayPoint we are at destination
            if (nextWayPointIndex == nbOfWayPoints) {
                isAtDestination = true;
                return;
            }

            if (travelDistance < distanceToWayPoint) {
                Point nextWayPoint = route.getWaypoints().get(nextWayPointIndex);
                double bearing = EarthCalc.getBearing(currentPoint, nextWayPoint); //in decimal degrees
                currentPoint = EarthCalc.pointRadialDistance(currentPoint, bearing, travelDistance);
            } else {
                isAtDestination = true;
            }
        }
    }

    private double distanceToNextWayPoint() {
        if (nextWayPointIndex == route.getWaypoints().size()) {
            return 0.0;
        }
        Point nextWayPoint = route.getWaypoints().get(nextWayPointIndex);
        return EarthCalc.getHarvesineDistance(currentPoint, nextWayPoint);
    }

    private double totalDistance() {
        double totDistance = 0.0;
        for (int i = 0; i < route.getWaypoints().size() - 1; i++) {
            totDistance += EarthCalc.getHarvesineDistance(route.getWaypoints().get(i), route.getWaypoints().get(i + 1));
        }

        return totDistance;
    }

    private double getTravelDistance() {
        long currentTime = System.nanoTime();
        double elapsedTimeSec = (currentTime - timestamp) / Math.pow(10, 9);
        currentSpeed = maxSpeed * RAND.nextDouble();
        double travelDistance = elapsedTimeSec * currentSpeed;
        timestamp = currentTime;

        return travelDistance;
    }

    public static void main(String[] args) throws InterruptedException {
        RouteGenerator routeGenerator = new RouteGenerator(new RandomGeo());

//        GPSRoute gpsRoute = routeGenerator.getRoute(1.0);
        GPSRoute gpsRoute = routeGenerator.getRoute(
                new Point(new DegreeCoordinate(44.427056), new DegreeCoordinate(26.102663)),
                new Point(new DegreeCoordinate(44.426205), new DegreeCoordinate(26.100601)));
        GPSUnit gpsUnit = new GPSUnit(72, gpsRoute);
        System.out.println(gpsUnit.totalDistance());
        long begin = System.nanoTime();

        while (!gpsUnit.isAtDestination()) {
            Thread.sleep(1000);
            gpsUnit.move();
        }
        long end = System.nanoTime();
        System.out.println((end - begin) / Math.pow(10, 9));
    }
}
