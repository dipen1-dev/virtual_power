package com.example.proshore.util;

import com.example.proshore.dto.PowerSourceDTO;
import com.example.proshore.model.PowerSource;
import com.example.proshore.model.Response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class Helper {

    public static ResponseEntity<Response> getResponseEntity(Object body, String message, boolean success) {
        return ResponseEntity.ok(new Response(body, message, success));
    }

    private static final String TEST_DATA_JSON_FILE_PATH = "/static/test-data.json";

    public static JsonNode getJsonNodeOfTestData() {
        try {
            // Load the JSON file as a resource
            Resource resource = new ClassPathResource(TEST_DATA_JSON_FILE_PATH);

            // Read the content of the JSON file
            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());

            // Convert the JSON bytes to a JSON node
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<PowerSourceDTO> getAllPowerSourceDTOSFromTestJsonData() {
        ObjectMapper objectMapper = new ObjectMapper();
        var jsonNode = Helper.getJsonNodeOfTestData();
        List<PowerSourceDTO> powerSources = null;
        try {
            powerSources = new ArrayList<>();
            if (jsonNode != null && jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    PowerSourceDTO powerSource = objectMapper.treeToValue(node, PowerSourceDTO.class);
                    powerSources.add(powerSource);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return powerSources;
    }

    public static List<PowerSource> getAllPowerSourceFromTestJsonData() {
        ObjectMapper objectMapper = new ObjectMapper();
        var jsonNode = Helper.getJsonNodeOfTestData();
        List<PowerSource> powerSources = null;
        try {
            powerSources = new ArrayList<>();
            if (jsonNode != null && jsonNode.isArray()) {
                for (JsonNode node : jsonNode) {
                    PowerSource powerSource = objectMapper.treeToValue(node, PowerSource.class);
                    powerSources.add(powerSource);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return powerSources;
    }

    public static boolean isNotNullAndEmpty(String str) {
        return str != null && !str.isEmpty();
    }

    public static List<PowerSource> getTransientPowerSourcesFromDTOS(List<PowerSourceDTO> powerSources) {
        return powerSources.stream().map(powerSource -> new PowerSource(
                powerSource.getName(), powerSource.getPostCode(), powerSource.getCapacity()
        )).toList();
    }
}
