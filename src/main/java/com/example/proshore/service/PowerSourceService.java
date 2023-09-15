package com.example.proshore.service;

import com.example.proshore.dao.PowerSourceDao;
import com.example.proshore.dto.PowerSourceDTO;
import com.example.proshore.dto.PowerSourceResponseDTO;
import com.example.proshore.model.PowerSource;
import com.example.proshore.model.Response;
import com.example.proshore.util.Helper;
import com.example.proshore.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing power sources.
 * <p>
 * This class provides services related to power sources, including saving power sources
 * and retrieving batteries based on postcode range.
 *
 * @author Dilip Babu Acharya
 * @version 1.0
 * @since 2023-09-15
 */
@Service
public class PowerSourceService {

    @Autowired
    public PowerSourceDao powerSourceDao;

    /**
     * Save a list of power sources.
     *
     * @param powerSources List of PowerSourceDTO objects to save
     * @return ResponseEntity containing the response
     */
    public ResponseEntity<Response> saveAllPowerSources(List<PowerSourceDTO> powerSources) {
        var success = false;
        String message;
        try {
            //setting data from pojo object to transient object using java stream API to persist data in database
            List<PowerSource> transientPowerSources = Helper.getTransientPowerSourcesFromDTOS(powerSources);
            success = powerSourceDao.saveAllData(transientPowerSources);
            message = success ? Strings.SAVED_SUCCESSFULLY : Strings.FAILED_TO_SAVE;
        } catch (Exception e) {
            e.printStackTrace();
            message = Strings.SOMETHING_WENT_WRONG;
        }
        return Helper.getResponseEntity(null, message, success);
    }

    /**
     * Retrieve batteries based on postcode range.
     * <p>
     * This method retrieves batteries where the postcodes fall within the given range.
     *
     * @param postCodeFrom Starting postcode of the range (can not be null)
     * @param postCodeTo   Ending postcode of the range (can not be null)
     * @return ResponseEntity containing the response with battery data like names of batteries, total and average watt'
     * capacity of batteries
     */

    public ResponseEntity<Response> getAllBatteriesByPostCodeRange(String postCodeFrom, String postCodeTo) {
        var success = false;
        String message = null;
        PowerSourceResponseDTO powerSourceResponse = null;
        try {
            var powerSources = powerSourceDao.getAllBatteries();
            if (powerSources == null || powerSources.isEmpty())
                return Helper.getResponseEntity(null, Strings.NO_DATE_FOUND, false);

            if (Helper.isNotNullAndEmpty(postCodeFrom) && Helper.isNotNullAndEmpty(postCodeTo)) {
                powerSources = powerSources.stream().filter(powerSource -> {
                    String postCode = powerSource.getPostCode();
                    return Integer.parseInt(postCode) >= Integer.parseInt(postCodeFrom) && Integer.parseInt(postCode) <= Integer.parseInt(postCodeTo);
                }).toList();
            }
            powerSourceResponse = new PowerSourceResponseDTO();
            List<String> batteries = powerSources.stream().map(PowerSourceDTO::getName).toList();
            double averageWatts = powerSources.stream().mapToDouble(PowerSourceDTO::getCapacity).average().orElse(0.0);
            int totalBatteries = powerSources.size();
            powerSourceResponse.setBatteries(batteries);
            powerSourceResponse.setAverageWattCapacities(averageWatts);
            powerSourceResponse.setTotal(totalBatteries);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
            message = Strings.SOMETHING_WENT_WRONG;
        }
        return Helper.getResponseEntity(powerSourceResponse, message, success);
    }
}
