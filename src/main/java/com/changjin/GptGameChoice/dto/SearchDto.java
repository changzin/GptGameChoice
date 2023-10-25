package com.changjin.GptGameChoice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class SearchDto {
    private List<String> tagList;
    private List<String> similarGames;
    private List<String> excludedGames;

    public SearchDto() {
        tagList = new ArrayList<>();
        similarGames = new ArrayList<>();
        excludedGames = new ArrayList<>();
    }

    public void AddTag(String tag){
        this.tagList.add(tag);
    }

    public void addSimilarGame(String GameName){
        this.similarGames.add(GameName);
    }

    public void addExcludedGame(String GameName){
        this.excludedGames.add(GameName);
    }
}
