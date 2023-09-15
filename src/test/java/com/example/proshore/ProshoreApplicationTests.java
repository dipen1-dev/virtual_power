package com.example.proshore;

import com.example.proshore.util.Helper;
import com.example.proshore.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class ProshoreApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void testSaveEndPoint() {
        //Arrange
        var jsonNode = Helper.getJsonNodeOfTestData();

        try {
            //Act
            ResultActions resultActions = mockMvc.perform(post("http://localhost:8083/api/power-source/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Objects.requireNonNull(jsonNode).toString()));

            //Assert
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success").value(true))
                    .andExpect(jsonPath("$.message").value(Strings.SAVED_SUCCESSFULLY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getBatteriesByPostCodeRangeTest() {
        //Arrange
        try {
            //Act
            ResultActions resultActions = mockMvc.perform(post("http://localhost:8083/api/power-source/batteries")
                    .param("postCodeFrom", "1")
                    .param("postCodeTo", "880"));

            //Assert
            resultActions
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success").value(true))
                    .andExpect(jsonPath("$.body.total").value(2))
                    .andExpect(jsonPath("$.body.averageWattCapacities").value(20250.0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
