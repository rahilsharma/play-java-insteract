
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "aairport",
    "acode",
    "dairport",
    "dcode",
    "tripType",
    "dDate",
    "travelDays",
    "suggested"
})
public class Item {

    @JsonProperty("aairport")
    private String aairport;
    @JsonProperty("acode")
    private String acode;
    @JsonProperty("dairport")
    private String dairport;
    @JsonProperty("dcode")
    private String dcode;
    @JsonProperty("tripType")
    private String tripType;
    @JsonProperty("dDate")
    private String dDate;
    @JsonProperty("travelDays")
    private Integer travelDays;
    @JsonProperty("suggested")
    private List<Integer> suggested = new ArrayList<Integer>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The aairport
     */
    @JsonProperty("aairport")
    public String getAairport() {
        return aairport;
    }

    /**
     * 
     * @param aairport
     *     The aairport
     */
    @JsonProperty("aairport")
    public void setAairport(String aairport) {
        this.aairport = aairport;
    }

    /**
     * 
     * @return
     *     The acode
     */
    @JsonProperty("acode")
    public String getAcode() {
        return acode;
    }

    /**
     * 
     * @param acode
     *     The acode
     */
    @JsonProperty("acode")
    public void setAcode(String acode) {
        this.acode = acode;
    }

    /**
     * 
     * @return
     *     The dairport
     */
    @JsonProperty("dairport")
    public String getDairport() {
        return dairport;
    }

    /**
     * 
     * @param dairport
     *     The dairport
     */
    @JsonProperty("dairport")
    public void setDairport(String dairport) {
        this.dairport = dairport;
    }

    /**
     * 
     * @return
     *     The dcode
     */
    @JsonProperty("dcode")
    public String getDcode() {
        return dcode;
    }

    /**
     * 
     * @param dcode
     *     The dcode
     */
    @JsonProperty("dcode")
    public void setDcode(String dcode) {
        this.dcode = dcode;
    }

    /**
     * 
     * @return
     *     The tripType
     */
    @JsonProperty("tripType")
    public String getTripType() {
        return tripType;
    }

    /**
     * 
     * @param tripType
     *     The tripType
     */
    @JsonProperty("tripType")
    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    /**
     * 
     * @return
     *     The dDate
     */
    @JsonProperty("dDate")
    public String getDDate() {
        return dDate;
    }

    /**
     * 
     * @param dDate
     *     The dDate
     */
    @JsonProperty("dDate")
    public void setDDate(String dDate) {
        this.dDate = dDate;
    }

    /**
     * 
     * @return
     *     The travelDays
     */
    @JsonProperty("travelDays")
    public Integer getTravelDays() {
        return travelDays;
    }

    /**
     * 
     * @param travelDays
     *     The travelDays
     */
    @JsonProperty("travelDays")
    public void setTravelDays(Integer travelDays) {
        this.travelDays = travelDays;
    }

    /**
     * 
     * @return
     *     The suggested
     */
    @JsonProperty("suggested")
    public List<Integer> getSuggested() {
        return suggested;
    }

    /**
     * 
     * @param suggested
     *     The suggested
     */
    @JsonProperty("suggested")
    public void setSuggested(List<Integer> suggested) {
        this.suggested = suggested;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
