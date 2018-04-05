package com.example.ConspectSite.services;

import com.example.ConspectSite.model.Tag;
import com.example.ConspectSite.repository.TagsRepository;
import com.example.ConspectSite.services.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TagsService {

    @Autowired
    private TagsRepository tagsRepository;

    public List<TagDTO> getTags(){
        Iterable<Tag> tags = tagsRepository.findAll();
        return StreamSupport.stream(tags.spliterator(), false)
                .map(TagDTO::new)
                .collect(Collectors.toList());
    }

}
