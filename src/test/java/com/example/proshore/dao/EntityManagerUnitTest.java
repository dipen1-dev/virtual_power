package com.example.proshore.dao;

import com.example.proshore.util.Helper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class EntityManagerUnitTest {
    @PersistenceContext
    EntityManager entityManager;

    @Test
    void testSaveAllData() {
        //Arrange

        //getting list of power source from test-data.json file
        var powerSources = Helper.getAllPowerSourceFromTestJsonData();

        //Act
        Long id = (Long) ((Session) entityManager.getDelegate()).save(powerSources.get(0));

        //Assert
        assertThat(id).isPositive();
    }
}
