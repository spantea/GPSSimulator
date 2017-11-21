package com.deloitte.osm;

import com.deloitte.gps.GPSRoute;
import com.deloitte.gps.RandomGeo;
import com.deloitte.osm.entities.Route;
import com.deloitte.osm.entities.Step;
import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.Point;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that generates routes between two different random gps locations
 * using OpenStretMap Api
 */
public class RouteGenerator {

    private static final Client client = ClientBuilder.newClient();
    private static final String OSM_ROUTER_API = "http://router.project-osrm.org/route/v1/driving/";

    private RandomGeo randomGeo;

    public RouteGenerator(RandomGeo randomGeo) {
        this.randomGeo = randomGeo;
    }

    public GPSRoute getRoute(double routeRange) {
      Point orign = randomGeo.randomPoint(RandomGeo.BUCHAREST_CENTER, routeRange / 2.0);
      Point destination = randomGeo.randomPoint(RandomGeo.BUCHAREST_CENTER, routeRange / 2.0);

      return getRoute(orign, destination);
    }

    public GPSRoute getRoute(Point origin, Point destination) {

        String orig = origin.getLongitude() + "," + origin.getLatitude();
        String dest = destination.getLongitude() + "," + destination.getLatitude();
        String location = orig + ";" + dest;

        Route route = client.target(OSM_ROUTER_API)
                .path(location)
                .queryParam("steps", true)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(Route.class);

        GPSRoute gpsRoute = osmRouteToGPSRoute(route);

        return gpsRoute;
    }

    private GPSRoute osmRouteToGPSRoute(Route route) {
        List<Point> gpsWaypoints = new ArrayList<>();
        List<Step> intermediateSteps =  route.getRoutes().get(0).getLegs().get(0).getSteps();
        for (int i = 0; i < intermediateSteps.size(); i++) {
            double latitude = intermediateSteps.get(i).getIntersections().get(0).getLocation().get(1);
            double longitude = intermediateSteps.get(i).getIntersections().get(0).getLocation().get(0);
            Point intermediateWaypoint = new Point(new DegreeCoordinate(latitude), new DegreeCoordinate(longitude));
            gpsWaypoints.add(intermediateWaypoint);
        }


        return new GPSRoute(gpsWaypoints);
    }

    public static void main(String[] args) {
        RandomGeo randomGeo = new RandomGeo();
        Point loc1 = randomGeo.randomPoint(RandomGeo.BUCHAREST_CENTER, 10);
        System.out.println(loc1);
        Point loc2 = randomGeo.randomPoint(RandomGeo.BUCHAREST_CENTER, 10);
        System.out.println(loc2);


        RouteGenerator routeGenerator = new RouteGenerator(randomGeo);
        GPSRoute testRoute = routeGenerator.getRoute(loc1, loc2);
        System.out.println(testRoute);
    }
}
