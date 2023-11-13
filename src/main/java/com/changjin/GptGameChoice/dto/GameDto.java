package com.changjin.GptGameChoice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
public class GameDto {
    private String gameName;
    private String gameReview;
    private int gamePrice;
    private List<String> gameTag;
    private String gameDescription;
    private String steamLink;
}
