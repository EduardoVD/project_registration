package br.edu.famper.projectregistration.service;

import br.edu.famper.projectregistration.model.Project;
import br.edu.famper.projectregistration.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project save(Project project) {return projectRepository.save(project);}

    public List<Project> findAll() {return projectRepository.findAll();}

    public Optional<Project> findById(Long id) {return projectRepository.findById(id);}

    public Project update(Project project) {return projectRepository.save(project);}

    public void delete(Long id) {projectRepository.deleteById(id);}

    public void deleteAll() {projectRepository.deleteAll();}

}
