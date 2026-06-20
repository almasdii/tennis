package TableTennis.dao.hibernateImpl;

import TableTennis.Exception.DataBaseException;
import TableTennis.dao.PlayerDao;
import TableTennis.entity.Player;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

@RequiredArgsConstructor
public class HibernatePlayerDaoImpl implements PlayerDao {
    private static final String FIND_BY_NAME = """
            SELECT p FROM Player p WHERE p.name = :name
            """;
    private static final String NAME = "name";
    private final SessionFactory sessionFactory;

    @Override
    public Player save(Player entity) {
        try {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.persist(entity);
            return entity;
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }

    public Optional<Player> findByName(String name){
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            return currentSession.createQuery(
                            FIND_BY_NAME, Player.class)
                    .setParameter(NAME, name)
                    .uniqueResultOptional();
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }
}
