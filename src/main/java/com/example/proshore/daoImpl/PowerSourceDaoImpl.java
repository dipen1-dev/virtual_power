package com.example.proshore.daoImpl;

import com.example.proshore.dao.PowerSourceDao;
import com.example.proshore.dto.PowerSourceDTO;
import com.example.proshore.model.PowerSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the PowerSourceDao interface.
 * This class interacts with the database to perform CRUD operations on PowerSource entities.
 *
 * @author Dilip Babu Acharya
 * @version 1.0
 * @since 2023-09-15
 */

@Repository
@Transactional
public class PowerSourceDaoImpl implements PowerSourceDao {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * Save a list of power sources.
     *
     * @param powerSources List of PowerSource objects to save
     * @return true if the data was saved successfully; false otherwise
     */
    @Override
    public boolean saveAllData(List<PowerSource> powerSources) {
        try {
            Session session = ((Session) entityManager.getDelegate());
            for (PowerSource powerSource : powerSources) {
                session.save(powerSource);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieve all batteries.
     *
     * @return a list of PowerSourceDTO objects representing batteries
     */
    @Override
    public List<PowerSourceDTO> getAllBatteries() {
        String jpql = """
                SELECT new com.example.proshore.dto.PowerSourceDTO(ps.name, ps.postCode, ps.capacity)
                FROM PowerSource ps
                ORDER BY ps.name ASC
                """;

        var query = entityManager.createQuery(jpql, PowerSourceDTO.class);
        return query.getResultList();

    }
}
