package com.example.proshore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a PowerSource entity.
 *
 * This class is used to model and store information about power sources.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-09-15
 */

@Entity
@Table(name = "tbl_power_source")
@Getter
@Setter
@NoArgsConstructor
public class PowerSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("postcode")
    @Column(name = "post_code")
    private String postCode;

    @JsonProperty("capacity")
    private double capacity;

    /**
     * Constructs a new PowerSource object with the specified properties.
     *
     * @param name     The name of the power source.
     * @param postCode The postal code (postcode) associated with the power source.
     * @param capacity The capacity of the power source.
     */
    public PowerSource(String name, String postCode, double capacity) {
        this.name = name;
        this.postCode = postCode;
        this.capacity = capacity;
    }
}
