package com.example.ConspectSite.services;

import com.example.ConspectSite.model.Conspect;
import com.example.ConspectSite.model.Tag;
import com.example.ConspectSite.model.User;
import com.example.ConspectSite.repository.ConspectRepository;
import com.example.ConspectSite.repository.TagsRepository;
import com.example.ConspectSite.repository.UserRepository;
import com.example.ConspectSite.security.models.UserAccountDetails;
import com.example.ConspectSite.services.dto.ConspectDTO;
import com.example.ConspectSite.services.dto.ConspectRequestDTO;
import com.example.ConspectSite.services.dto.ConspectResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConspectService {

    @Autowired
    private ConspectRepository conspectRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TagsRepository tagsRepository;
    @Autowired
    private UserRepository userRepository;

    public ConspectResponseDTO getUserConspects(String email){
        if(email == null){
            Long id = ((UserAccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
            return new ConspectResponseDTO(conspectRepository.findAllByUserId(id));
        } else {

            return new ConspectResponseDTO(conspectRepository.findAllByUserId(userRepository.findByEmail(email).getId()));
        }
    }

    public List<ConspectDTO> getFreshConspects(){
        return new ConspectResponseDTO(conspectRepository.findAll()).getConspects();
    }

    public ConspectDTO getConspect(Long conspectId, String userEmail){
        ConspectDTO conspectDTO = null;
        Conspect conspect = conspectRepository.findById(conspectId).orElse(new Conspect());
        if(conspect.getUser().getEmail().equals(userEmail) || userEmail == null) {
            conspectDTO = new ConspectDTO(conspect);
            conspectDTO.setComments(conspect.getComments());
        }
        return conspectDTO;
    }

    public void deleteConspect(Long id){
        conspectRepository.deleteById(id);
    }

    @Transactional
    public void createConspect(String email, ConspectRequestDTO conspectRequestDTO){
        User user = userService.findUser(email);
        Conspect conspect = new Conspect(conspectRequestDTO, user);
        conspect.setTags(getTags(conspectRequestDTO.getTags()));
        conspectRepository.save(conspect);
    }

    @Transactional
    public void updateConspect(ConspectDTO conspectDTO){
        Conspect conspect = conspectRepository.findById(conspectDTO.getId()).orElse(new Conspect());
        conspect.setDataFromDTO(conspectDTO);
        conspect.setTags(getTags(conspectDTO.getTags()));
        conspectRepository.save(conspect);
    }

    private List<Tag> getTags(List<String> tagStrings){
        List<Tag> tags = new ArrayList<>();
        for(String t: tagStrings){
            Tag tag = tagsRepository.findByTag(t);
            if(tag == null){
                Tag newTag = new Tag(t, 1);
                tagsRepository.save(newTag);
                tags.add(newTag);
            } else {
                tag.setWeight(tag.getWeight() + 1);
                tags.add(tag);
            }
        }
        return tags;
    }

}
