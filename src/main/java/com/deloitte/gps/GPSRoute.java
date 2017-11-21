package com.deloitte.gps;

import com.grum.geocalc.Point;

import java.util.List;

/**
 * Route POJO
 */
public class GPSRoute {

    private List<Point> waypoints;

    public GPSRoute(List<Point> waypoints) {
        this.waypoints = waypoints;
    }

    public List<Point> getWaypoints() {
        return waypoints;
    }

    @Override
    public String toString() {
        return "GPSRoute{" +
                "waypoints=" + waypoints +
                '}';
    }
}