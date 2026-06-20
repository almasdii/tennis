package TableTennis.dao;

import TableTennis.entity.MatchEntity;

import java.util.List;

public interface MatchDao extends Dao<MatchEntity> {
    List<MatchEntity> findAll(int limit, int offset);
    List<MatchEntity> findAllByName(int limit, int offset, String name);
    Long countTotalMatches();
    Long countTotalMatches(String playerName);
}

