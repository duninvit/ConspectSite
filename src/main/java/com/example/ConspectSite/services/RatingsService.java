package com.example.ConspectSite.services;

import com.example.ConspectSite.model.Conspect;
import com.example.ConspectSite.model.Rating;
import com.example.ConspectSite.model.User;
import com.example.ConspectSite.repository.ConspectRepository;
import com.example.ConspectSite.repository.RatingRepository;
import com.example.ConspectSite.services.dto.ConspectRatingDTO;
import com.example.ConspectSite.services.dto.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingsService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ConspectRepository conspectRepository;
    @Autowired
    private UserService userService;

    public ConspectRatingDTO getConspectRating(Long conspectId) {
        ConspectRatingDTO conspectRatingDTO = new ConspectRatingDTO();
        Double averageRate = averageRating(conspectId);
        conspectRatingDTO.setAverageRate(averageRate.floatValue());
        User user = userService.findUser(null);
        if (user != null) {
            Integer myRate = 0;
            try {
                myRate = ratingRepository.getRatingByUserIdAndConspectId(user.getId(), conspectId).getRate();
            } catch (Exception e) {
                myRate = 0;
            }
            conspectRatingDTO.setMyRate(myRate);
        }
        return conspectRatingDTO;
    }

    private double averageRating(Long conspectId) {
        List<Rating> ratings = ratingRepository.findAllByConspectId(conspectId);
        double totalRate = 0;
        for (Rating rate : ratings) {
            totalRate += (double) rate.getRate();
        }
        return totalRate / ratingRepository.countAllByConspectId(conspectId);
    }

    public void addRatingToConspect(Long conspectId, ConspectRatingDTO rating) {
        Conspect conspect = conspectRepository.findById(conspectId).orElse(new Conspect());
        User user = userService.findUser(null);
        if (ratingRepository.getRatingByUserIdAndConspectId(user.getId(), conspectId) != null) {
            ratingRepository.delete(ratingRepository.getRatingByUserIdAndConspectId(user.getId(), conspectId));
        }
        Rating newRate = new Rating(rating.getMyRate(), user, conspect);
        ratingRepository.save(newRate);
        conspect.getRates().add(newRate);
        conspectRepository.save(conspect);
    }

}
