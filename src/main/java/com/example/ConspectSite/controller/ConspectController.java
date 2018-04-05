package com.example.ConspectSite.controller;

import com.example.ConspectSite.model.Conspect;
import com.example.ConspectSite.repository.ConspectRepository;
import com.example.ConspectSite.services.ConspectService;
import com.example.ConspectSite.services.TagsService;
import com.example.ConspectSite.services.dto.ConspectDTO;
import com.example.ConspectSite.services.dto.ConspectRequestDTO;
import com.example.ConspectSite.services.dto.ConspectResponseDTO;
import com.example.ConspectSite.services.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/conspects")
public class ConspectController {

    @Autowired
    private ConspectService conspectService;
    @Autowired
    private TagsService tagsService;

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    public ConspectResponseDTO getMyConspects(@RequestParam(value = "email", required = false) String email) {
        return conspectService.getUserConspects(email);
    }

    @GetMapping("/fresh")
    @ResponseStatus(HttpStatus.OK)
    public List<ConspectDTO> getFreshConspect(){
        return conspectService.getFreshConspects();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public void createConspect(@RequestParam(value = "email", required = false) String email,
                                 @RequestBody ConspectRequestDTO conspectRequestDTO){
        conspectService.createConspect(email, conspectRequestDTO);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateConspect(@RequestBody ConspectDTO conspectDTO){
        conspectService.updateConspect(conspectDTO);
    }

    @GetMapping("/get/{fanfictionId}")
    @ResponseStatus(HttpStatus.OK)
    public ConspectDTO getConspect(@PathVariable Long fanfictionId,
                                     @RequestParam(value = "email", required = false) String userEmail){
        return conspectService.getConspect(fanfictionId, userEmail);
    }

    @PostMapping("/delete/{fanfictionId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteConspect (@PathVariable Long fanfictionId){
        conspectService.deleteConspect(fanfictionId);
    }

    @GetMapping("/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDTO> getTags(){
        return tagsService.getTags();
    }

}
