package logunov.maxim.data.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import logunov.maxim.domain.entity.Error;
import logunov.maxim.domain.entity.ErrorType;
import retrofit2.HttpException;

public class ErrorParserTransformer {

    private Gson gson;

    public ErrorParserTransformer(Gson gson) {
        this.gson = gson;
    }

    public <T, E extends Throwable> ObservableTransformer<T, T> parseHttpError() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

                return upstream
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(Throwable throwable) throws Exception {

                                Error error;
                                if (throwable instanceof HttpException) {
                                    HttpException httpException = (HttpException) throwable;
                                    String errorBody = httpException.response().errorBody().toString();
                                    E httpError = gson.fromJson(errorBody,
                                            new TypeToken<E>() {
                                            }.getType());
                                    error = new Error(httpError.getMessage(),
                                            ErrorType.SERVER_ERROR);

                                } else if (throwable instanceof UnknownHostException) {
                                    error = new Error("Server is not available",
                                            ErrorType.SERVER_IS_NOT_AVAILABLE);
                                } else if (throwable.getMessage().contains("404")) {
                                    error = new Error("Url error 404",
                                            ErrorType.SERVER_ERROR);
                                } else if (throwable instanceof SocketTimeoutException
                                        || throwable instanceof ConnectException) {
                                    error = new Error("Internet is not available",
                                            ErrorType.INTERNET_IS_NOT_AVAILABLE);
                                } else {
                                    error = new Error("Unexpected error",
                                            ErrorType.UNEXPECTED_ERROR);
                                }
                                return Observable.error(error);
                            }
                        });
            }
        };
    }
}
