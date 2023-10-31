package com.changjin.GptGameChoice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    private int tagId;
    private String tagName;
    private Boolean check;
    private int lcs;


    public TagDto(int tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.check = false;
        this.lcs = 0;
    }
}
