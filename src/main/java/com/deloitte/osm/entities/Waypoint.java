
package com.deloitte.osm.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Waypoint {

    @SerializedName("hint")
    @Expose
    private String hint;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private List<Double> location = null;

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

}
