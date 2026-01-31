package com.example.formconstructor.service;

import com.example.formconstructor.entity.Project;
import com.example.formconstructor.repository.jpa.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import org.springframework.lang.NonNull;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    public Project createProject(String name) {
        Project project = new Project();
        project.setName(name);
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(@NonNull Long id) {
        return projectRepository.findById(id);
    }

    @Transactional
    public Project updateCurrentSnapshot(@NonNull Long projectId, String snapshotId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        project.setCurrentSnapshotId(snapshotId);
        return projectRepository.save(project);
    }
}
