
package com.deloitte.osm.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route {

    @SerializedName("routes")
    @Expose
    private List<Route_> routes = null;
    @SerializedName("waypoints")
    @Expose
    private List<Waypoint> waypoints = null;
    @SerializedName("code")
    @Expose
    private String code;

    public List<Route_> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route_> routes) {
        this.routes = routes;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
