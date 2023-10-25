package com.changjin.GptGameChoice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TagDto {
    private int tagId;
    private String tagName;
    private Boolean check;
}
