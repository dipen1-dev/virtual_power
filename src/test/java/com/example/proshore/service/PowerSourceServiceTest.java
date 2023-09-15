package com.example.proshore.service;

import com.example.proshore.dto.PowerSourceDTO;
import com.example.proshore.dto.PowerSourceResponseDTO;
import com.example.proshore.model.Response;
import com.example.proshore.util.Helper;
import com.example.proshore.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PowerSourceServiceTest {

    @Autowired
    PowerSourceService powerSourceService;

    @Test
    void powerSourceTest() {
        try {
            // Arrange

            // getting list of power source from test-data.json file
            var powerSources = Helper.getAllPowerSourceDTOSFromTestJsonData();

            // Act
            var response = powerSourceService.saveAllPowerSources(powerSources);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(Strings.SAVED_SUCCESSFULLY, Objects.requireNonNull(response.getBody()).getMessage());
            assertTrue(Objects.requireNonNull(response.getBody()).isSuccess());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllBatteriesByCodeRange() {
        try {
            // Arrange
            // getting list of power source from test-data.json file
            var powerSources = Helper.getAllPowerSourceDTOSFromTestJsonData();

            // Act
            powerSourceService.saveAllPowerSources(powerSources);
            var responseEntity = powerSourceService.getAllBatteriesByPostCodeRange("1", "880");
            Response response = responseEntity.getBody();
            PowerSourceResponseDTO powerSource = (PowerSourceResponseDTO) response.getBody();


            // Assert
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertTrue(response.isSuccess());
            assertEquals(2, powerSource.getTotal());
            assertEquals(20250.0, powerSource.getAverageWattCapacities());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
