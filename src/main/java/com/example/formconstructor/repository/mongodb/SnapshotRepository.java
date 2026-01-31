package com.example.formconstructor.repository.mongodb;

import com.example.formconstructor.domain.FormSnapshot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnapshotRepository extends MongoRepository<FormSnapshot, String> {
    List<FormSnapshot> findByProjectId(Long projectId);
}
