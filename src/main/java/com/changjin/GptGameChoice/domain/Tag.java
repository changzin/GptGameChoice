package com.changjin.GptGameChoice.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.stereotype.Controller;

@Entity
@Getter
@Table(name = "tag")
public class Tag {
    @Id
    private int tagId;
    private String tagName;
}