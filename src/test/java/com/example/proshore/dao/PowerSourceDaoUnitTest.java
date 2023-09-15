package com.example.proshore.dao;

import com.example.proshore.daoImpl.PowerSourceDaoImpl;
import com.example.proshore.dto.PowerSourceDTO;
import com.example.proshore.model.PowerSource;
import com.example.proshore.util.Helper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class PowerSourceDaoUnitTest {
    @InjectMocks
    private PowerSourceDaoImpl powerSourceDaoImpl;

    @Mock
    private EntityManager entityManager;

    @Mock
    private Session session;

    @Mock
    private TypedQuery<Object> query;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveAllData() {
        // Arrange
        List<PowerSource> powerSources = Helper.getAllPowerSourceFromTestJsonData(); // get test power sources
        List<PowerSourceDTO> powerSourceDTOs = Helper.getAllPowerSourceDTOSFromTestJsonData(); // get test power source DTOs

        // Mock the behavior of the session
        when(entityManager.getDelegate()).thenReturn(session); // Mock the EntityManager behavior

        // Act
        boolean success = powerSourceDaoImpl.saveAllData(powerSources);

        // Assert
        assertTrue(success);

        // Verify that the save method was called on the session for each power source
        verify(session, times(powerSources.size())).save(any(PowerSource.class));
    }

    @Test
    void getAllBatteries() {
        // Arrange
        List<PowerSource> powerSources = Helper.getAllPowerSourceFromTestJsonData(); // Create test power sources
        List<PowerSourceDTO> powerSourceDTOs = Helper.getAllPowerSourceDTOSFromTestJsonData(); // Create test power source DTOs

        // Mock the behavior of the session
        when(entityManager.getDelegate()).thenReturn(session); // Mock the EntityManager behavior

        //Mock the behaviour of the query
        when(entityManager.createQuery(anyString(), any())).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(powerSourceDTOs));

        // Act
        // save one power source
        boolean success = powerSourceDaoImpl.saveAllData(Collections.singletonList(powerSources.get(0)));
        List<PowerSourceDTO> powerSourceDTOList = powerSourceDaoImpl.getAllBatteries();

        // Assert
        assertTrue(success);
        assertThat(powerSourceDTOList.size()).isEqualTo(1); // Verify that the list size is 1
    }
}
