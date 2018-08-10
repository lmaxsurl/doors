package logunov.maxim.data.network;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
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
    private static final String REQUEST_URL =
            "https://api.backendless.com/1EBA3425-DB44-22DD-FFF0-1F00CF757E00/16478945-A0BC-408D-FF5E-C4D6CA9F4800/";
    private static final String STRING_TYPE_FORMAT = "type LIKE '%s'";
    private static final String STRING_ID_FORMAT = "id LIKE %d";
    private static final int CONNECTION_TIME = 15;
    private static final String TYPE = "type";


    @Inject
    public RestService(){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .readTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIME, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        gson = new GsonBuilder()
                .create();

        this.restApi = new Retrofit
                .Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(REQUEST_URL)
                .client(okHttpClient)
                .build()
                .create(RestApi.class);

        errorParserTransformer = new ErrorParserTransformer(gson);
    }

    public Observable<List<DoorResponse>> getAllDoors(String doorClass, String doorType){
        return restApi
                .getDoors(doorClass, String.format(STRING_TYPE_FORMAT, doorType))
                .compose(errorParserTransformer.<List<DoorResponse>, HttpError>parseHttpError());
    }

    public Observable<List<TypeResponse>> getTypes(String doorClass){
        return restApi
                .getDoorTypes(doorClass, TYPE)
                .compose(errorParserTransformer.<List<TypeResponse>, HttpError>parseHttpError());
    }

    public Observable<DoorResponse> getDoor(String doorClass, String id){
        return restApi
                .getDoor(doorClass, id)
                .compose(errorParserTransformer.<DoorResponse, HttpError>parseHttpError());
    }

}
