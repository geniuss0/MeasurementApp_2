package com.measurementapp_2.dao;

import com.measurementapp_2.entity.Measurement;
import com.measurementapp_2.entity.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Project> getProjects() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Project> query = currentSession.createQuery(
                "from Project", Project.class);

        return query.getResultList();
    }

    @Override
    public void saveProject(Project project) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(project);
    }

    @Override
    public Project getProject(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        Project project = currentSession.get(Project.class, id);

        return project;
    }

    @Override
    public void deleteProject(int id) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.delete(currentSession.get(Project.class, id));
    }

    @Override
    public List<Measurement> getMeasurements(int id) {

        Session session = sessionFactory.getCurrentSession();

        Project project = session.load(Project.class, id);

        List<Measurement> list = project.getMeasurements();

        list.size();

        return list;
    }
}
