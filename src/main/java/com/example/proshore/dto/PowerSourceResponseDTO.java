package com.example.proshore.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PowerSourceResponseDTO {
    private List<String> batteries;

    private int total;

    private double averageWattCapacities;
}
