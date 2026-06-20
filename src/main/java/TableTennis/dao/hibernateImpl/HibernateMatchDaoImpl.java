package TableTennis.dao.hibernateImpl;

import TableTennis.Exception.DataBaseException;
import TableTennis.dao.MatchDao;
import TableTennis.entity.MatchEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class HibernateMatchDaoImpl implements MatchDao {
    private static final String NAME = "name";
    private static final String FIND_ALL_QUERY = """
            SELECT m
            FROM MatchEntity m
            LEFT JOIN FETCH m.firstPlayer f
            LEFT JOIN FETCH m.secondPlayer s
            LEFT JOIN FETCH m.winner w
            ORDER BY m.id DESC
            """;

    private static final String FIND_ALL_BY_NAME_QUERY = """
            SELECT m FROM MatchEntity m
            LEFT JOIN FETCH m.firstPlayer f
            LEFT JOIN FETCH m.secondPlayer s
            WHERE
            f.name LIKE :name
            OR s.name LIKE :name
            """;

    private static final String COUNT_MATCHES = """
            SELECT count(m) 
            FROM MatchEntity m
            """;
    public static final String COUNT_MATCHES_BY_NAME = """
            SELECT count(m) 
            FROM MatchEntity m
            JOIN m.firstPlayer f
            JOIN m.secondPlayer s
            WHERE
                f.name LIKE :name
                OR s.name LIKE :name
            """;

    private final SessionFactory sessionFactory;

    @Override
    public List<MatchEntity> findAll(int limit, int offset) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.createQuery(FIND_ALL_QUERY, MatchEntity.class)
                    .setMaxResults(limit)
                    .setFirstResult(offset).getResultList();
        } catch (HibernateException exception) {
            throw new DataBaseException(exception);
        }
    }

    @Override
    public List<MatchEntity> findAllByName(int limit, int offset, String playerName) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.createQuery(FIND_ALL_BY_NAME_QUERY, MatchEntity.class)
                    .setParameter(NAME, "%" + playerName + "%")
                    .setFirstResult(offset)
                    .setMaxResults(limit).list();
        } catch (HibernateException exception) {
            throw new HibernateException(exception);
        }
    }

    @Override
    public Long countTotalMatches() {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession
                    .createQuery(COUNT_MATCHES, Long.class)
                    .getSingleResult();
        } catch (HibernateException exception) {
            throw new DataBaseException(exception);
        }
    }

    @Override
    public Long countTotalMatches(String playerName) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession
                    .createQuery(COUNT_MATCHES_BY_NAME, Long.class)
                    .setParameter(NAME, "%" + playerName + "%")
                    .getSingleResult();
        } catch (HibernateException exception) {
            throw new DataBaseException(exception);
        }
    }

    @Override
    public MatchEntity save(MatchEntity match) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.persist(match);
            return match;
        } catch (HibernateException exception) {
            throw new DataBaseException(exception);
        }
    }

}
