package com.measurementapp_2.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Measurement> measurements;


    public Project() {}

    public Project(String name, List<Measurement> measurements) {
        this.name = name;
        this.measurements = measurements;
    }


    public void add(Measurement measurement) {

        if(measurements == null) {
            measurements = new ArrayList<>();
        }

        measurements.add(measurement);
        measurement.setProject(this);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }


    @Override
    public String toString() {
        return Integer.toString(id);
    }
}

