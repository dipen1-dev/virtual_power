package com.example.proshore.service;

import com.example.proshore.dao.PowerSourceDao;
import com.example.proshore.dto.PowerSourceDTO;
import com.example.proshore.dto.PowerSourceResponseDTO;
import com.example.proshore.model.Response;
import com.example.proshore.util.Helper;
import com.example.proshore.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PowerSourceServiceUnitTest {
    @InjectMocks
    private PowerSourceService powerSourceService;

    @Mock
    private PowerSourceDao powerSourceDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void powerSourceUnitTest() {
        // Arrange
        List<PowerSourceDTO> powerSourceDTOS = Helper.getAllPowerSourceDTOSFromTestJsonData(); // Create test power source DTOs

        // Mock the behavior of the repository
        when(powerSourceDao.saveAllData(anyList())).thenReturn(true);

        // Act
        ResponseEntity<Response> response = powerSourceService.saveAllPowerSources(powerSourceDTOS);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).isSuccess());
        assertEquals(Strings.SAVED_SUCCESSFULLY, Objects.requireNonNull(response.getBody()).getMessage());

        verify(powerSourceDao, times(1)).saveAllData(anyList());
    }

    @Test
    void getAllBatteriesByCodeRange() {
        try {
            // Arrange
            // getting list of power source from test-data.json file
            var powerSources = Helper.getAllPowerSourceDTOSFromTestJsonData();
            when(powerSourceDao.getAllBatteries()).thenReturn(powerSources);
            // Act
            powerSourceService.saveAllPowerSources(powerSources);
            var responseEntity = powerSourceService.getAllBatteriesByPostCodeRange("1", "880");
            Response response = responseEntity.getBody();
            PowerSourceResponseDTO responseBody = (PowerSourceResponseDTO) response.getBody();


            // Check the values in the JSON response
            int total = responseBody.getTotal();
            java.util.List<String> batteries = responseBody.getBatteries();
            double averageWattCapacity = responseBody.getAverageWattCapacities();

            // Assert
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertTrue(response.isSuccess());
            assertEquals(2, total);
            assertEquals(20250.0, averageWattCapacity);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
