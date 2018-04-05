package com.example.ConspectSite.repository;

import com.example.ConspectSite.model.Rating;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {

   /* @Query(value = "SELECT rate FROM rating r INNER JOIN fanfiction_rates c ON r.id=c.rates_id WHERE r.user_account_id= :userId AND c.fanfiction_id= :fanfictionId",
            nativeQuery = true)
    Integer getUserRate(@Param("userId") Long userId, @Param("fanfictionId") Long fanfictionId);*/

   Rating getRatingByUserIdAndConspectId(long userId, long conspectId);

   /* @Query(value = "SELECT AVG(rate) FROM rating r INNER JOIN fanfiction_rates c ON r.id = c.rates_id WHERE c.fanfiction_id = :fanfictionId",
            nativeQuery = true)
    Double getAverageRateByFanfictionId(@Param("fanfictionId") Long fanfictionId);*/

   Integer countAllByConspectId(long conspectId);

   List<Rating> findAllByConspectId(long conspectId);

}
