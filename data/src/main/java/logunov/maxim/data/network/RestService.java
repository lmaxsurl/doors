package logunov.maxim.data.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import logunov.maxim.data.BuildConfig;
import logunov.maxim.data.entity.DescriptionResponse;
import logunov.maxim.data.entity.DoorResponse;
import logunov.maxim.data.entity.HttpError;
import logunov.maxim.data.entity.TypeResponse;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RestService {

    private RestApi restApi;
    private Gson gson;
    private ErrorParserTransformer errorParserTransformer;
    private final String TYPE_ID_EQUALS = "type_id = ";
    private final String SORT_BY_TYPE = "title asc";
    private final String SORT_BY_ID = "id asc";
    private final int MAX_SIZE = 100;

    @Inject
    public RestService(String url, int timeout) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient
                .Builder()
                .readTimeout(timeout, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS);

        // add logs when it's debug
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpBuilder.addInterceptor(logging);
        }

        gson = new GsonBuilder()
                .create();

        this.restApi = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(url)
                .client(okHttpBuilder.build())
                .build()
                .create(RestApi.class);

        errorParserTransformer = new ErrorParserTransformer(gson);
    }

    public Observable<List<DoorResponse>> getDoors(String doorClass, int typeId,
                                                   int offset, int pageSize) {
        return restApi
                .getDoors(doorClass, offset, pageSize, TYPE_ID_EQUALS + typeId, SORT_BY_TYPE)
                .compose(errorParserTransformer.<List<DoorResponse>, HttpError>parseHttpError());
    }

    public Observable<List<TypeResponse>> getTypes(int offset, int pageSize) {
        return restApi
                .getTypes(offset, pageSize, SORT_BY_ID)
                .compose(errorParserTransformer.<List<TypeResponse>, HttpError>parseHttpError());
    }

    public  Observable<List<DescriptionResponse>> getDescriptions(){
        return restApi
                .getDescriptions(MAX_SIZE, SORT_BY_ID)
                .compose(errorParserTransformer.<List<DescriptionResponse>, Throwable>parseHttpError());
    }

}
