package com.technical.task.task.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {

    LocalDateTime analyseDate;
    LocalDateTime firstPost;
    LocalDateTime lastPost;
    long totalPosts;
    long totalAcceptedPosts;
    float avgScore;

}