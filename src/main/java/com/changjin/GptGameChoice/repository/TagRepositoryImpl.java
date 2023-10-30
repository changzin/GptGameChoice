package com.changjin.GptGameChoice.repository;

import com.changjin.GptGameChoice.domain.Tag;
import com.changjin.GptGameChoice.dto.TagDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository{

    private final EntityManager em;

    @Override
    public List<TagDto> findAllTags() {

        List<Tag> tagList = em.createQuery("select t from Tag t", Tag.class).getResultList();
        List<TagDto> tagDtoList = new ArrayList<>();

        for (Tag tag : tagList) {
            tagDtoList.add(new TagDto(tag.getTagId(), tag.getTagName()));
        }

        return tagDtoList;
    }
}
