package db;

// This interface is from Figure 1 of the assignment
public interface InMemoryDB {
    Integer get(String key); // This was changed to an Integer class so that it can accept null values

    void put(String key, int val);
  
    void begin_transaction();
  
    void commit();
  
    void rollback();
}
