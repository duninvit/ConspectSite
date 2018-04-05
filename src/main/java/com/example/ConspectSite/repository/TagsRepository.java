package com.example.ConspectSite.repository;

import com.example.ConspectSite.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagsRepository extends CrudRepository<Tag, String> {
    Tag findByTag(String tag);
}
