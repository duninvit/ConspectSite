package com.example.ConspectSite.repository;

import com.example.ConspectSite.model.Rating;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {

   Rating getRatingByUserIdAndConspectId(long userId, long conspectId);

   Integer countAllByConspectId(long conspectId);

   List<Rating> findAllByConspectId(long conspectId);

}
