
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class Item {
    private String aairport;
    private String acode;
    private String dairport;
    private String dcode;
    private String tripType;
    private String dDate;
    private Integer travelDays;
    private List<Integer> suggested = new ArrayList<Integer>();
    public String getAairport() {
        return aairport;
    }
    public void setAairport(String aairport) {
        this.aairport = aairport;
    }
    public String getAcode() {
        return acode;
    }
    public void setAcode(String acode) {
        this.acode = acode;
    }
    public String getDairport() {
        return dairport;
    }
    public void setDairport(String dairport) {
        this.dairport = dairport;
    }
    public String getDcode() {
        return dcode;
    }
    public void setDcode(String dcode) {
        this.dcode = dcode;
    }
    public String getTripType() {
        return tripType;
    }
    public void setTripType(String tripType) {
        this.tripType = tripType;
    }
    public String getDDate() {
        return dDate;
    }
    public void setDDate(String dDate) {
        this.dDate = dDate;
    }
    public Integer getTravelDays() {
        return travelDays;
    }
    public void setTravelDays(Integer travelDays) {
        this.travelDays = travelDays;
    }
    public List<Integer> getSuggested() {
        return suggested;
    }
    public void setSuggested(List<Integer> suggested) {
        this.suggested = suggested;
    }
}
