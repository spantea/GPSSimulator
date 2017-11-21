
package com.deloitte.osm.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Intersection {

    @SerializedName("out")
    @Expose
    private Integer out;
    @SerializedName("entry")
    @Expose
    private List<Boolean> entry = null;
    @SerializedName("bearings")
    @Expose
    private List<Integer> bearings = null;
    @SerializedName("location")
    @Expose
    private List<Double> location = null;
    @SerializedName("in")
    @Expose
    private Integer in;

    public Integer getOut() {
        return out;
    }

    public void setOut(Integer out) {
        this.out = out;
    }

    public List<Boolean> getEntry() {
        return entry;
    }

    public void setEntry(List<Boolean> entry) {
        this.entry = entry;
    }

    public List<Integer> getBearings() {
        return bearings;
    }

    public void setBearings(List<Integer> bearings) {
        this.bearings = bearings;
    }

    public List<Double> getLocation() {
        return location;
    }

    public void setLocation(List<Double> location) {
        this.location = location;
    }

    public Integer getIn() {
        return in;
    }

    public void setIn(Integer in) {
        this.in = in;
    }

}
