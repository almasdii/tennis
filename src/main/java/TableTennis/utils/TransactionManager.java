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
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        boolean isOwner = !transaction.isActive(); // мы открыли транзакцию?

        if (isOwner) {
            transaction.begin();
        }

        try {
            T result = action.get();
            if (isOwner) transaction.commit();
            return result;
        } catch (Exception e) {
            if (isOwner) transaction.rollback();
            throw e;
        } finally {
            if (isOwner) session.close();
        }
    }
    public void doInTransaction(Runnable action){
        doInTransaction(()->{
            action.run();
            return null;
        });
    }
}
