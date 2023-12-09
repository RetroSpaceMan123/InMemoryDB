package db;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class HashMapDB implements InMemoryDB {

    HashMap<String, Integer> db;
    Queue<DBOperation> q;
    boolean inTransaction;
    
    public HashMapDB() {
        db = new HashMap<>();
        q = new LinkedList<>();
        inTransaction = false;
    }
    
    @Override
    public Integer get(String key) {
        return db.get(key);
    }

    @Override
    public void put(String key, int val) {
        if (inTransaction) {
            q.add(new DBOperation(key, val));
        } else throw new IllegalStateException("A transaction must be in progress in order for you to use this method");
    }

    @Override
    public void begin_transaction() {
        if (inTransaction) {
            throw new IllegalStateException("A transaction is already in progress");
        }
        
        inTransaction = true;
    }

    @Override
    public void commit() {
        if (!inTransaction) {
            throw new IllegalStateException("A transaction must be in progress in order for you to use this method");
        }
        
        while (!q.isEmpty()) {
            DBOperation op = q.poll();
            db.put(op.getKey(), op.getValue());
        }
        inTransaction = false;
    }

    @Override
    public void rollback() {
        if (!inTransaction) {
            throw new IllegalStateException("A transaction must be in progress in order for you to use this method");
        }

        q.clear();
        inTransaction = false;
    }
    
}
