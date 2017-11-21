
package com.deloitte.osm.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Maneuver {

    @SerializedName("bearing_after")
    @Expose
    private Integer bearingAfter;
    @SerializedName("bearing_before")
    @Expose
    private Integer bearingBefore;
    @SerializedName("location")
    @Expose
    private List<Double> location = null;
    @SerializedName("modifier")
    @Expose
    private String modifier;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("exit")
    @Expose
    private Integer exit;

    public Integer getBearingAfter() {
        return bearingAfter;
    }

    public void setBearingAfter(Integer bearingAfter) {
        this.bearingAfter = bearingAfter;
    }

    public Integer getBearingBefore() {
        return bearingBefore;
    }

    public void setBearingBefore(Integer bearingBefore) {
        this.bearingBefore = bearingBefore;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getExit() {
        return exit;
    }

    public void setExit(Integer exit) {
        this.exit = exit;
    }

}
