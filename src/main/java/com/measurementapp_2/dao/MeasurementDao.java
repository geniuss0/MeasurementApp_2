package com.measurementapp_2.dao;

import com.measurementapp_2.entity.Measurement;

public interface MeasurementDao {

    void saveMeasurement(Measurement measurement);

    Measurement getMeasurement(int id);

    void deleteMeasurement(int id);
}
