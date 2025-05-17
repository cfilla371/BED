package org.launchcode.BingeBuddy.data;

import org.launchcode.BingeBuddy.model.Watchlist;
import org.launchcode.BingeBuddy.model.WatchlistStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
    List<Watchlist> findAllByUserId(Integer userId);

    List<Watchlist> findByUser_Id(Integer userId);
}
