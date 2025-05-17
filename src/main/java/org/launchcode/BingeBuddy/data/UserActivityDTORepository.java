package org.launchcode.BingeBuddy.data;

import org.launchcode.BingeBuddy.dto.UserActivityDTO;
import org.launchcode.BingeBuddy.model.Comment;
import org.launchcode.BingeBuddy.model.Review;
import org.launchcode.BingeBuddy.model.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserActivityDTORepository extends JpaRepository<Comment, Integer> {

    //@Query("SELECT new org.launchcode.BingeBuddy.dto.UserActivityDTO(u.count(userId), u.count(userId), u.count(userId))
    // from UserActivityDTO uDTO where uDTO.userId = :1" )

    //    //Query 1:
    @Query(value =
            " select "  +
                    "(select count(user_id) from testbingebuddy.review where user_id= :user_id group by user_id) reviews," +
                    "(select count(user_id) from testbingebuddy.comment where user_id= :user_id group by user_id) comments," +
                    "(select count(user_id) from testbingebuddy.watchlist where user_id= :user_id group by user_id) watchlists ", nativeQuery = true)
    public List<Object[]> getCurrentUserActivity(@Param("user_id") Integer userId);



//    //Query 2:
//    //Query 2.0:
//    @Query(value= "select count(user_id) from testbingebuddy.review where user_id= :user_id group by user_id")
//    public Review getCurrentUserReviewCount(@Param("user_id") int user_id);
//
//    //Query 2.1:
//    @Query(value = "select count(user_id) from testbingebuddy.comment where user_id= :user_id group by user_id")
//    public Comment getCurrentUserCommentCount(@Param("user_id") int user_id);
//
//    //Query 2.2:
//    @Query(value = "select count(user_id) from testbingebuddy.watchlist where user_id= :user_id group by user_id")
//    public Watchlist getCurrentUserWatchlistCount(@Param("user_id") int user_id);


}
