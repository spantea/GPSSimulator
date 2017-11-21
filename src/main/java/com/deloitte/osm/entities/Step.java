
package com.deloitte.osm.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Step {

    @SerializedName("intersections")
    @Expose
    private List<Intersection> intersections = null;
    @SerializedName("geometry")
    @Expose
    private String geometry;
    @SerializedName("mode")
    @Expose
    private String mode;
    @SerializedName("maneuver")
    @Expose
    private Maneuver maneuver;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("ref")
    @Expose
    private String ref;
    @SerializedName("rotary_name")
    @Expose
    private String rotaryName;

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public void setIntersections(List<Intersection> intersections) {
        this.intersections = intersections;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Maneuver getManeuver() {
        return maneuver;
    }

    public void setManeuver(Maneuver maneuver) {
        this.maneuver = maneuver;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRotaryName() {
        return rotaryName;
    }

    public void setRotaryName(String rotaryName) {
        this.rotaryName = rotaryName;
    }

}
