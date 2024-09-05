package br.edu.famper.projectregistration.repository;

import br.edu.famper.projectregistration.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {}
