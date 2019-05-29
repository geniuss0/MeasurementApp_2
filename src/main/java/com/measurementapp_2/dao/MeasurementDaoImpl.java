package com.measurementapp_2.dao;

import com.measurementapp_2.entity.Measurement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MeasurementDaoImpl implements MeasurementDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveMeasurement(Measurement measurement) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(measurement);
    }

    @Override
    public Measurement getMeasurement(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Measurement measurement = currentSession.get(Measurement.class, id);

        return measurement;
    }

    @Override
    public void deleteMeasurement(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Query query = currentSession.createQuery(
                "delete from Measurement where id=:mId");
        query.setParameter("mId", id);

        query.executeUpdate();
    }
}
