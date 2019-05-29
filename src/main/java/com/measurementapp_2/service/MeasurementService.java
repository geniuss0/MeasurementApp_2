package com.measurementapp_2.service;

import com.measurementapp_2.entity.Measurement;

public interface MeasurementService {

    void SaveMeasurement(Measurement measurement);

    Measurement getMeasurement(int id);

    void deleteMeasurement(int id);
}
