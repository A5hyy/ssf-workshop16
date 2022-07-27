package project.workshop16.workshop16app.model;

import java.io.Serializable;
import java.util.Random;

public class Checkers implements Serializable {
    
    private String id;
    private String name;
    private Pieces pieces;
    private int updateCount;
    private boolean upsert;
    private int insertCount;

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Checkers() {
        this.id = generateId(8);
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public boolean isUpsert() {
        return upsert;
    }

    public void setUpsert(boolean upsert) {
        this.upsert = upsert;
    }

    public int getInsertCount() {
        return insertCount;
    }

    public void setInsertCount(int insertCount) {
        this.insertCount = insertCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pieces getPieces() {
        return pieces;
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }

    private synchronized String generateId(int numchars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numchars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numchars);
    }
}
