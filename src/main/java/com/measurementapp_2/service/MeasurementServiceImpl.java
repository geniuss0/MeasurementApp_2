package com.measurementapp_2.service;

import com.measurementapp_2.dao.MeasurementDao;
import com.measurementapp_2.entity.Measurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementDao measurementDao;

    @Override
    @Transactional
    public void SaveMeasurement(Measurement measurement) {

        measurementDao.saveMeasurement(measurement);
    }

    @Override
    @Transactional
    public Measurement getMeasurement(int id) {

        return measurementDao.getMeasurement(id);
    }

    @Override
    @Transactional
    public void deleteMeasurement(int id) {

        measurementDao.deleteMeasurement(id);
    }
}
