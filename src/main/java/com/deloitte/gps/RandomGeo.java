package com.deloitte.gps;




import com.grum.geocalc.DegreeCoordinate;
import com.grum.geocalc.Point;

import java.util.Random;

/**
 * Utility class for generating random gps points in an area around a
 * predefined location.
 * https://stackoverflow.com/questions/31192451/generate-random-geo-coordinates-within-specific-radius-from-seed-point
 */
public class RandomGeo {

    public static final Point BUCHAREST_CENTER = new Point(
            new DegreeCoordinate(44.4268),
            new DegreeCoordinate(26.1025));
    private static final Random RAND = new Random(1);

    public Point randomPoint(Point center, double radiusKm) {
        radiusKm = radiusKm * 1000;

        double y0 = center.getLatitude();
        double x0 = center.getLongitude();
        double rd = radiusKm / 111300.0;

        double u = RAND.nextDouble();
        double v = RAND.nextDouble();

        double w = rd * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        double xp = x / Math.cos(y0);

        double newlat = y + y0;
        double newlon = x + x0;
        return new Point(new DegreeCoordinate(newlat) , new DegreeCoordinate(newlon));
    }

    public static void main(String[] args) {
//        Location testVal = new Location("test value in bucharest", 44.35690221154821, 26.08541782289642);
//        RandomGeo randomGeo = new RandomGeo();
//        GeoHelper geoHelper = new GeoHelper();
//
//        for (int i = 0; i < 100; i++) {
//            Location randPoint = randomGeo.randomPoint(BUCHAREST_CENTER, 100);
//            System.out.println(randPoint.latitude + ", " + randPoint.longitude + " distance: "
//                    + geoHelper.distance(BUCHAREST_CENTER.latitude, BUCHAREST_CENTER.longitude, randPoint.latitude, randPoint.longitude));
//        }
//
////        System.out.println(GeoHelper.calcGeoDistanceInKm(bucharestCenter, testVal));
    }
}
