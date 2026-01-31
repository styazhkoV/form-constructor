package com.example.formconstructor.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "snapshots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormSnapshot {

    @Id
    private String id;

    private Long projectId;
    private Integer version;
    private List<SectionSnapshot> sections;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SectionSnapshot {
        private String title;
        private List<QuestionSnapshot> questions;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionSnapshot {
        private String text;
        private String answer;
        private String type; // e.g., TEXT, MULTI_CHOICE
        private String aiHint;
    }
}
