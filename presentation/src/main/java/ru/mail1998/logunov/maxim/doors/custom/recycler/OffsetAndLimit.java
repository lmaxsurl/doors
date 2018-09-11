package ru.mail1998.logunov.maxim.doors.custom.recycler;

public class OffsetAndLimit {

    private int offset;
    private int limit;

    public OffsetAndLimit(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
