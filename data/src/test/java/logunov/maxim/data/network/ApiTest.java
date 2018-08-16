package logunov.maxim.data.network;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import logunov.maxim.data.R;
import logunov.maxim.data.entity.DoorResponse;
import logunov.maxim.domain.entity.Error;
import logunov.maxim.domain.entity.ErrorType;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.Mockito.when;

public class ApiTest {

    private static final String BASE_URL = "/1EBA3425-DB44-22DD-FFF0-1F00CF757E00/16478945-A0BC-408D-FF5E-C4D6CA9F4800/";
    private static final int OKHTTP_TIMEOUT_SECOND = 1;

    private RestService restService;

    private MockWebServer mockServer;

    @Before
    public void setUp() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();
        HttpUrl url = mockServer.url(BASE_URL);
        restService = new RestService(url.toString(), OKHTTP_TIMEOUT_SECOND);
    }

    @After
    public void release() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void testError404Response() {

        MockResponse mockedResponse = new MockResponse();
        mockedResponse.setResponseCode(404);

        mockServer.enqueue(mockedResponse);

        TestObserver<List<DoorResponse>> observer = TestObserver.create();

        restService
                .getAllDoors("df", "fd")
                .subscribe(observer);

        observer.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) {
                if (throwable instanceof Error) {
                    Error error = (Error) throwable;
                    return error.getType() == ErrorType.SERVER_ERROR;
                }
                return false;
            }
        });


        observer.dispose();
    }

    @Test
    public void testErrorTimeoutsResponse() {

        TestObserver<List<DoorResponse>> observer = TestObserver.create();

        restService
                .getAllDoors("fd", "fd")
                .subscribe(observer);
        observer.awaitTerminalEvent(OKHTTP_TIMEOUT_SECOND + 1, TimeUnit.SECONDS);

        observer.assertError(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) {

                if (throwable instanceof Error) {
                    Error error = (Error) throwable;

                    System.out.println(error.toString());

                    return error.getType() == ErrorType.INTERNET_IS_NOT_AVAILABLE;
                }

                return false;
            }
        });

        observer.dispose();
    }
}

