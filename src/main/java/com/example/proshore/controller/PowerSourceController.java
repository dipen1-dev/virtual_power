package com.example.proshore.controller;

import com.example.proshore.dto.PowerSourceDTO;
import com.example.proshore.dto.PowerSourceResponseDTO;
import com.example.proshore.model.Response;
import com.example.proshore.service.PowerSourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class handles HTTP requests related to power sources.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-09-15
 */

@RestController
@RequestMapping("api/power-source")
public class PowerSourceController {
    @Autowired
    PowerSourceService powerSourceService;

    /**
     * Add a list of power sources.
     *
     * @param powerSources List of PowerSourceDTO objects to add
     * @return ResponseEntity containing the response
     */
    @Operation(summary = "Add Batteries", description = "Add a list of power sources")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = Response.class)))
    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Response> addBatteries(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "List of power sources",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PowerSourceDTO.class))
                    )
            ) @RequestBody List<PowerSourceDTO> powerSources
    ) {
        return powerSourceService.saveAllPowerSources(powerSources);
    }

    /**
     * Retrieve batteries with postcodes within the specified range.
     *
     * This endpoint allows you to retrieve batteries based on their postcodes,
     * where the postcodes fall within the given range.
     *
     * @param postCodeFrom Starting postcode of the range (required)
     * @param postCodeTo Ending postcode of the range (required)
     * @return A ResponseEntity containing the response with batteries, total and average watt capacity
     *
     * @since 2023-09-15
     * @version 1.0
     */
    @Operation(summary = "Get Batteries", description = "Get batteries having postcode between the given range")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content(schema = @Schema(implementation = PowerSourceResponseDTO.class)))
    @PostMapping("batteries")
    public ResponseEntity<Response> getAllBatteriesByPostCodeRange(
            @Parameter(name = "postCodeFrom", required = true, description = "Starting postcode", in = ParameterIn.QUERY)
            @RequestParam String postCodeFrom,
            @Parameter(name = "postCodeTo", required = true, description = "Ending postcode", in = ParameterIn.QUERY)
            @RequestParam String postCodeTo
    ) {
        return powerSourceService.getAllBatteriesByPostCodeRange(postCodeFrom, postCodeTo);
    }
}
