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

    @GetMapping("/gets")
    @ResponseStatus(HttpStatus.OK)
    public List<ConspectDTO> getConspects(@RequestParam(value = "id") String id){
        return conspectService.getConspects(Long.parseLong(id));
    }

    @GetMapping("/getbytag")
    @ResponseStatus(HttpStatus.OK)
    public List<ConspectDTO> getConspectsByTag(@RequestParam(value = "tag") String tag){
        return conspectService.getConspectsByTag(tag);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Long createConspect(@RequestParam(value = "email", required = false) String email,
                                 @RequestBody ConspectRequestDTO conspectRequestDTO){
        return conspectService.createConspect(email, conspectRequestDTO);
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateConspect(@RequestBody ConspectDTO conspectDTO){
        conspectService.updateConspect(conspectDTO);
    }

    @GetMapping("/get/{conspectId}")
    @ResponseStatus(HttpStatus.OK)
    public ConspectDTO getConspect(@PathVariable Long conspectId,
                                     @RequestParam(value = "email", required = false) String userEmail){
        return conspectService.getConspect(conspectId, userEmail);
    }

    @PostMapping("/delete/{conspectId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteConspect (@PathVariable Long conspectId){
        conspectService.deleteConspect(conspectId);
    }

    @GetMapping("/tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDTO> getTags(){
        return tagsService.getTags();
    }

}
