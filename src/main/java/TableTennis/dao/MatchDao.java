package TableTennis.dao;

import TableTennis.entity.MatchEntity;

import java.util.List;

public interface MatchDao extends Dao<MatchEntity> {
    List<MatchEntity> findAll(int pageNumber, int pageSize);
    List<MatchEntity> findAllByName(int pageNumber, int pageSize, String name);
    Long countTotalMatches();
    Long countTotalMatches(String playerName);
}

