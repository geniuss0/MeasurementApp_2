package com.measurementapp_2.controller;

import com.measurementapp_2.entity.Project;
import com.measurementapp_2.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/list")
    public String listProjects(Model model) {

        List<Project> projects = projectService.getProjects();

        model.addAttribute("projects", projects);

        Project project = new Project();

        model.addAttribute("project", project);

        return "project-list";
    }

    @PostMapping("/addNewProjectForm")
    public String addNewProjectForm(@ModelAttribute("project") Project project) {

        //project.setName(project.getName().toLowerCase());
        projectService.saveProject(project);

        return "redirect:/project/list";
    }

}
