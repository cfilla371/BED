package org.launchcode.BingeBuddy.data;

import org.launchcode.BingeBuddy.model.Comment;
import org.launchcode.BingeBuddy.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByReview_Id(Integer reviewId);

    List<Comment> findByUserId(Integer userID);


}
