package com.example.formconstructor.controller;

import com.example.formconstructor.domain.FormSnapshot;
import com.example.formconstructor.entity.Project;
import com.example.formconstructor.service.ProjectService;
import com.example.formconstructor.service.SnapshotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.lang.NonNull;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final SnapshotService snapshotService;

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestParam String name) {
        return ResponseEntity.ok(projectService.createProject(name));
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PostMapping("/{projectId}/snapshots")
    public ResponseEntity<FormSnapshot> createSnapshot(
            @PathVariable @NonNull Long projectId,
            @RequestBody List<FormSnapshot.SectionSnapshot> sections) {
        return ResponseEntity.ok(snapshotService.createSnapshot(projectId, sections));
    }

    @GetMapping("/{projectId}/snapshots")
    public ResponseEntity<List<FormSnapshot>> getHistory(@PathVariable @NonNull Long projectId) {
        return ResponseEntity.ok(snapshotService.getHistory(projectId));
    }
}
