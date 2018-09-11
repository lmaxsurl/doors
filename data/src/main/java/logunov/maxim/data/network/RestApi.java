package logunov.maxim.data.network;

import java.util.List;

import io.reactivex.Observable;
import logunov.maxim.data.entity.DescriptionResponse;
import logunov.maxim.data.entity.DoorResponse;
import logunov.maxim.data.entity.TypeResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestApi {

    @GET("data/{class}")
    Observable<List<DoorResponse>> getDoors(@Path("class") String doorClass,
                                            @Query("offset") int offset,
                                            @Query("pageSize") int size,
                                            @Query("where") String type,
                                            @Query("sortBy") String sort);

    @GET("data/{class}")
    Observable<List<TypeResponse>> getDoorTypes(@Path("class") String doorClass,
                                                @Query("pageSize") int size,
                                                @Query("props") String type,
                                                @Query("sortBy") String sort);
    @GET("data/description")
    Observable<List<DescriptionResponse>> getDescriptions(@Query("pageSize") int size,
                                                          @Query("sortBy") String sort);


}
