package TableTennis.service;

import TableTennis.dao.MatchDao;
import TableTennis.dto.PaginationData;
import TableTennis.dto.PaginationDto;
import TableTennis.entity.MatchEntity;
import TableTennis.mapper.Mapper;
import TableTennis.mapper.PaginationDtoMapper;
import TableTennis.utils.TransactionManager;
import TableTennis.validator.MatchValidator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FinishedMatchesPersistenceService {
    private final MatchDao matchDao;
    private final TransactionManager transactionManager;
    private final MatchValidator validator = new MatchValidator();
    private final Mapper<PaginationDto, PaginationData> mapper = new PaginationDtoMapper();

    public void save(MatchEntity match) {
        transactionManager.doInTransaction(()->{
            matchDao.save(match);
        });
    }

    public PaginationDto findAll(String playerName, int pageNumber, int pageSize) {
        validator.validatePage(pageNumber);
        validator.validateFilterName(playerName);
        int limit = pageSize;
        int offset = pageNumber * pageSize;

        List<MatchEntity> matchEntities = transactionManager.doInTransaction(() -> {
            List<MatchEntity> matches;
            if (playerName == null) {
                matches = matchDao.findAll(limit, offset);
            } else {
                matches = matchDao.findAllByName(limit, offset, playerName);
            }
            return matches;
        });
        PaginationData paginationData = new PaginationData(matchEntities,numberOfPages(playerName,limit),pageNumber,limit);
        return mapper.mapFrom(paginationData);
    }

    public int numberOfPages(String playerName,int limit){
        return transactionManager.doInTransaction(() -> {
            if (playerName != null && !playerName.isEmpty()) {
                return (int) Math.ceil(
                        matchDao.countTotalMatches(playerName)
                                / (double) limit
                );
            }
            return (int) Math.ceil(
                    matchDao.countTotalMatches()
                            / (double) limit
            );
        });
    }
}


