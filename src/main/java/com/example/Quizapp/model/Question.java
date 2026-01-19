package com.example.Quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question_title", nullable = false)
    private String questionTitle;

    @Column(nullable = false)
    private String option1;

    @Column(nullable = false)
    private String option2;

    @Column(nullable = false)
    private String option3;

    @Column(nullable = false)
    private String option4;

    @Column(name = "right_answer", nullable = false)
    private String rightAnswer;

    @Column(name = "difficulty_level", nullable = false)
    private String difficultyLevel;

    @Column(nullable = false)
    private String category;
}
