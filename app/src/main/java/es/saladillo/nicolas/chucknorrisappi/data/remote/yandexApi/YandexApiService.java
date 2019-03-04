package es.saladillo.nicolas.chucknorrisappi.data.remote.yandexApi;

import es.saladillo.nicolas.chucknorrisappi.data.remote.chNuAppi.ChNuAppi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YandexApiService {
    private static YandexApiService INSTANCE;
    private final YandexApi yandexApi;

    private YandexApiService(YandexApi swapi) {
        this.yandexApi = swapi;
    }

    public static YandexApiService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new YandexApiService(buildYandexApiInstance());
        }
        return INSTANCE;
    }

    private static YandexApi buildYandexApiInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://translate.yandex.net/api/v1.5/tr.json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(YandexApi.class);
    }

    public YandexApi getYandexApi() {
        return yandexApi;
    }
}
