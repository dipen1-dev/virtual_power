package com.example.proshore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
//@ApiModel(description = "Details about a power source")
public class PowerSourceDTO {
    @JsonIgnore
    private BigInteger id;

    @JsonProperty("name")
//    @ApiModelProperty(notes = "The name of the power source")
    private String name;

    @JsonProperty("postcode")
//    @ApiModelProperty(notes = "The postcode of the power source")
    private String postCode;

    @JsonProperty("capacity")
//    @ApiModelProperty(notes = "The capacity of the power source")
    private double capacity;

    public PowerSourceDTO(String name, String postCode, double capacity) {
        this.name = name;
        this.postCode = postCode;
        this.capacity = capacity;
    }
}
