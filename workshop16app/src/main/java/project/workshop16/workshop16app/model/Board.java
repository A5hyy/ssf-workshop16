package vttp.workshop16.workshop16.model;

public class Board {
    private long totalCount;
    private Cross squares;

    public long getTotalCount() { return totalCount; }
    public void setTotalCount(long value) { this.totalCount = value; }

    public Cross getSquares() { return squares; }
    public void setSquares(Cross value) { this.squares = value; }
}

