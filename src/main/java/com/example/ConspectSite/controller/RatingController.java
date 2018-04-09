package com.example.ConspectSite.controller;

import com.example.ConspectSite.services.RatingsService;
import com.example.ConspectSite.services.dto.ConspectRatingDTO;
import com.example.ConspectSite.services.dto.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rate")
public class RatingController {

    @Autowired
    private RatingsService ratingsService;

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public ConspectRatingDTO getConspectRating(@RequestParam("conspectId") Long conspectId){
        return ratingsService.getConspectRating(conspectId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public void addConspectRating(@RequestParam("conspectId") Long conspectId,
                                 @RequestBody ConspectRatingDTO rating){
        ratingsService.addRatingToConspect(conspectId, rating);
    }

}
