package ru.mail1998.logunov.maxim.doors.custom.recycler;

public class AutoLoadingRecyclerViewException extends RuntimeException {

    public AutoLoadingRecyclerViewException() {
        super("Exception in AutoLoadingRecyclerView");
    }

    public AutoLoadingRecyclerViewException(String detailMessage) {
        super(detailMessage);
    }
}
