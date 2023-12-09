package db;

public class DBOperation {
    private String key;
    private Integer value;

    public DBOperation(String k, Integer val) {
        key = k;
        value = val;
    }
    
    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}
