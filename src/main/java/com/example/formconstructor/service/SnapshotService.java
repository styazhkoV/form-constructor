package com.example.formconstructor.service;

import com.example.formconstructor.domain.FormSnapshot;
import com.example.formconstructor.repository.mongodb.SnapshotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.lang.NonNull;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SnapshotService {

    private final SnapshotRepository snapshotRepository;
    private final ProjectService projectService;

    @Transactional
    public FormSnapshot createSnapshot(@NonNull Long projectId, List<FormSnapshot.SectionSnapshot> sections) {
        // 1. Get all existing snapshots to determine the next version
        List<FormSnapshot> existingSnapshots = snapshotRepository.findByProjectId(projectId);
        int nextVersion = existingSnapshots.stream()
                .mapToInt(FormSnapshot::getVersion)
                .max()
                .orElse(0) + 1;

        // 2. Create the new snapshot document
        FormSnapshot snapshot = new FormSnapshot();
        snapshot.setProjectId(projectId);
        snapshot.setVersion(nextVersion);
        snapshot.setSections(sections);

        FormSnapshot savedSnapshot = snapshotRepository.save(snapshot);

        // 3. Update the metadata in PostgreSQL
        projectService.updateCurrentSnapshot(projectId, savedSnapshot.getId());

        return savedSnapshot;
    }

    public List<FormSnapshot> getHistory(@NonNull Long projectId) {
        return snapshotRepository.findByProjectId(projectId);
    }
}
