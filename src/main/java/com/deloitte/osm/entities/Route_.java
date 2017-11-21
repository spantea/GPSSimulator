
package com.deloitte.osm.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Route_ {

    @SerializedName("geometry")
    @Expose
    private String geometry;
    @SerializedName("legs")
    @Expose
    private List<Leg> legs = null;
    @SerializedName("weight_name")
    @Expose
    private String weightName;
    @SerializedName("weight")
    @Expose
    private Double weight;
    @SerializedName("duration")
    @Expose
    private Double duration;
    @SerializedName("distance")
    @Expose
    private Double distance;

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public String getWeightName() {
        return weightName;
    }

    public void setWeightName(String weightName) {
        this.weightName = weightName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
