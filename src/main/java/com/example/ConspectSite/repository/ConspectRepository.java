package com.example.ConspectSite.repository;

import com.example.ConspectSite.model.Conspect;
import com.example.ConspectSite.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConspectRepository extends CrudRepository<Conspect, Long> {

    List<Conspect> findAllByUserId(long id);

    List<Conspect> findAll();

   // List<Conspect> findAllAndOrderByCreationDate();

    List<Conspect> findAllByTags(Tag tag);
}
