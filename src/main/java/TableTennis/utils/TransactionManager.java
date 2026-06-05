package TableTennis.utils;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class TransactionManager {
    private final SessionFactory sessionFactory;

    public <T> T doInTransaction(Supplier<T> action){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            T t = action.get();
            transaction.commit();
            return t;
        }catch (Exception e){
            session.getTransaction().rollback();
            throw e;
        }finally {
            session.close();
        }
    }
    public void doInTransaction(Runnable action){
        doInTransaction(()->{
            action.run();
            return null;
        });
    }
}
