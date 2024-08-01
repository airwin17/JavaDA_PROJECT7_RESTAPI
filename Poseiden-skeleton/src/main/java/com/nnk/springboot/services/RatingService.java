package com.nnk.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.exceptions.RequestedObjectNotFoundException;
import com.nnk.springboot.repositories.RatingRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingService {
private final RatingRepository ratingRepository;

public Rating saveRating(Rating rating) {
    return ratingRepository.save(rating);
}

public List<Rating> getRatings() {
    return ratingRepository.findAll();
}

public Rating getRatingById(int id) throws RequestedObjectNotFoundException{
    return ratingRepository.findByRatingid(id).orElseThrow(()-> new RequestedObjectNotFoundException("Rating not found with id: " + id));
}
@Transactional
public void deleteRating(int id) {
    ratingRepository.deleteByRatingid(id);
}

}
