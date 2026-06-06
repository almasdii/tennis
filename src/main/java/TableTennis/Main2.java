package TableTennis;

import TableTennis.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.LoggerFactory;

import java.lang.management.PlatformLoggingMXBean;
import java.sql.*;
import java.util.logging.Logger;

@Slf4j
public class Main2 {
    public static void main(String[] args) throws SQLException {
//        Configuration configuration = new Configuration();
//        configuration.configure();
        log.trace("");
        log.debug("");
        log.info("");
        log.warn("");
        log.error("");
//
//        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
//            Session session = sessionFactory.openSession();
//            Player player = session.get(Player.class,10);
//            session.evict(player);
//            session.clear();
//            Player player2 = session.get(Player.class,10);
//            System.out.println(player);
//            System.out.println(player2);
//            session.close();
//        }
    }
}