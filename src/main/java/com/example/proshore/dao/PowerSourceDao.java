package com.example.proshore.dao;

import com.example.proshore.dto.PowerSourceDTO;
import com.example.proshore.model.PowerSource;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing PowerSource entities.
 * <p>
 * This interface defines methods for saving power sources and retrieving battery data.
 *
 * @author Dilip Babu Acharya
 * @version 1.0
 * @since 2023-09-15
 */

public interface PowerSourceDao {
    /**
     * Save a list of power sources.
     *
     * @param powerSources List of PowerSource objects to save
     * @return true if the data was saved successfully; false otherwise
     */
    boolean saveAllData(List<PowerSource> powerSources);

    /**
     * Retrieve all batteries.
     *
     * @return a list of PowerSourceDTO objects representing batteries
     */
    List<PowerSourceDTO> getAllBatteries();
}
