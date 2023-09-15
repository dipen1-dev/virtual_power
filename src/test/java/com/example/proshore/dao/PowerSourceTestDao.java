package com.example.proshore.dao;

import com.example.proshore.daoImpl.PowerSourceDaoImpl;
import com.example.proshore.util.Helper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PowerSourceTestDao {
    @Autowired
    PowerSourceDaoImpl powerSourceDao;

    @Test
    void testSaveAllData() throws JsonProcessingException {
        //Arrange

        //getting list of power source from test-data.json file
        var powerSources = Helper.getAllPowerSourceFromTestJsonData();

        //Act
        boolean success = powerSourceDao.saveAllData(powerSources);

        //Assert
        assertTrue(success);
    }
}
