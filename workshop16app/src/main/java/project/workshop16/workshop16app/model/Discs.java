package project.workshop16.workshop16app.model;

public class Discs {
    private long totalCount;
    private Type[] types;

    public long getTotalCount() { return totalCount; }
    public void setTotalCount(long value) { this.totalCount = value; }

    public Type[] getTypes() { return types; }
    public void setTypes(Type[] value) { this.types = value; }
}