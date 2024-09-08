package br.edu.famper.projectregistration.controller;

import br.edu.famper.projectregistration.model.Project;
import br.edu.famper.projectregistration.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<Project>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(projectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Project> project = projectService.findById(id);

        if (project.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(project);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Project With ID " + id + " Not Found...");
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Project project) {

        if (project.getName() == null || project.getName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Project 'Name' Cannot Be Empty...");
        } else if (project.getInitialDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Project 'Initial Date' Cannot Be Empty...");
        } else {
            Project savedProject = projectService.save(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Project project) {
        Optional<Project> existingProject = projectService.findById(id);

        if (existingProject.isPresent()) {
            Project projectToUpdate = existingProject.get();
            projectToUpdate.setName(project.getName() != null ? project.getName() : projectToUpdate.getName());
            projectToUpdate.setInitialDate(project.getInitialDate() != null ? project.getInitialDate() : projectToUpdate.getInitialDate());
            projectToUpdate.setDescription(project.getDescription() != null ? project.getDescription() : projectToUpdate.getDescription());
            projectToUpdate.setFinalDate(project.getFinalDate() != null ? project.getFinalDate() : projectToUpdate.getFinalDate());

            Project updatedProject = projectService.update(projectToUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(updatedProject);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Project With ID " + id + " Not Found...");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Project> project = projectService.findById(id);

        if (project.isPresent()) {
            projectService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Project With ID " + id + " Not Found, Nothing to Delete...");
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        projectService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All Project's Deleted...");
    }
}
