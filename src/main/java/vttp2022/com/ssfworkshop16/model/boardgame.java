package vttp2022.com.ssfworkshop16.model;

import java.io.Serializable;
import java.util.Random;

public class boardgame implements Serializable{
    private String name;
    private pieces pieces;
    private String id;
    private int insertCount;
    private int updateCount;
    private boolean upsert;

    public boardgame() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private synchronized String generateId(int numchars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numchars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numchars);
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public pieces getPieces() {
        return pieces;
    }

    public void setPieces(pieces value) {
        this.pieces = value;
    }
}
