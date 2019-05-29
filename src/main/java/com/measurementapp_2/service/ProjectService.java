package com.measurementapp_2.service;

import com.measurementapp_2.entity.Measurement;
import com.measurementapp_2.entity.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjects();

    void saveProject(Project project);

    Project getProject(int id);

    void deleteProject(int id);

    List<Measurement> getMeasurements(int id);
}
