package com.measurementapp_2.service;

import com.measurementapp_2.dao.ProjectDao;
import com.measurementapp_2.entity.Measurement;
import com.measurementapp_2.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;


    @Override
    @Transactional
    public List<Project> getProjects() {

        return projectDao.getProjects();
    }

    @Override
    @Transactional
    public void saveProject(Project project) {

        projectDao.saveProject(project);
    }

    @Override
    @Transactional
    public Project getProject(int id) {

        return projectDao.getProject(id);
    }

    @Override
    @Transactional
    public void deleteProject(int id) {

        projectDao.deleteProject(id);
    }

    @Override
    @Transactional
    public List<Measurement> getMeasurements(int id) {

        return projectDao.getMeasurements(id);
    }
}
