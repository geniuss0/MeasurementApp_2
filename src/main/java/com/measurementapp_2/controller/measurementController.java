package com.measurementapp_2.controller;

import com.measurementapp_2.entity.Measurement;
import com.measurementapp_2.entity.Project;
import com.measurementapp_2.fileSupport.PdfSupport;
import com.measurementapp_2.service.MeasurementService;
import com.measurementapp_2.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/measurement")
public class measurementController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private MeasurementService measurementService;

    private Project tmpProject;

    @GetMapping("/list")
    public ModelAndView listMeasurements(@RequestParam("projectId") int projectId, Model model) {

        List<Measurement> measurements = projectService.getMeasurements(projectId);
        Project project = projectService.getProject(projectId);
        Measurement measurement = new Measurement();

        model.addAttribute("inListMeasurement", measurement);
        model.addAttribute("measurements", measurements);
        tmpProject = project;
        project.setMeasurements(measurements);

        return new ModelAndView("measurement-list", "project", project);
    }

    @PostMapping("saveMeasurement")
    public String saveMeasurement(@ModelAttribute("measurement") Measurement measurement) {

        measurement.setProject(tmpProject);
        measurementService.SaveMeasurement(measurement);

        return "redirect:/measurement/list?projectId=" + tmpProject.getId();
    }

    @PostMapping("addNewMeasurement")
    public String addNewMeasurement(@ModelAttribute("newMeasurement") Measurement measurement, Model model) {

        measurement.setProject(tmpProject);
        measurementService.SaveMeasurement(measurement);

        return "redirect:/measurement/list?projectId=" + tmpProject.getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("measurementId") int id) {

        measurementService.deleteMeasurement(id);

        return "redirect:/measurement/list?projectId=" + tmpProject.getId();
    }

    @PostMapping("saveProjectName")
    public String saveProjectName(@ModelAttribute("project") Project project) {

        //project.setName(project.getName().toLowerCase());
        project.setMeasurements(tmpProject.getMeasurements());
        projectService.saveProject(project);

        return "redirect:/measurement/list?projectId=" + tmpProject.getId();
    }

    @GetMapping("/deleteProject")
    public String deleteProject() {

        projectService.deleteProject(tmpProject.getId());

        return "redirect:/project/list";
    }

    @GetMapping("/download")
    public void downloadProject(HttpServletResponse response) {

        String path = PdfSupport.generate(tmpProject);

        PdfSupport.download(response, path);

        PdfSupport.delete(path);
    }
}
