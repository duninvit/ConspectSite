package com.example.ConspectSite.repository;

import com.example.ConspectSite.model.Conspect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConspectRepository extends CrudRepository<Conspect, Long> {

    List<Conspect> findAllByUserId(long id);

    List<Conspect> findAll();
}
