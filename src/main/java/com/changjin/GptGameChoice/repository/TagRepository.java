package com.changjin.GptGameChoice.repository;

import com.changjin.GptGameChoice.domain.Tag;
import com.changjin.GptGameChoice.dto.TagDto;

import java.util.List;

public interface TagRepository {
    List<TagDto> findAllTags();
}
