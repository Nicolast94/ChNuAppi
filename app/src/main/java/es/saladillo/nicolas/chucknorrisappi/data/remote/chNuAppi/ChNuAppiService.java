package es.saladillo.nicolas.chucknorrisappi.data.remote.chNuAppi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChNuAppiService {

    private static ChNuAppiService INSTANCE;
    private final ChNuAppi chNuAppi;

    private ChNuAppiService(ChNuAppi swapi) {
        this.chNuAppi = swapi;
    }

    public ChNuAppi getChNuAppi() {
        return chNuAppi;
    }

    public static ChNuAppiService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChNuAppiService(buildChNuAppiInstance());
        }
        return INSTANCE;
    }

    private static ChNuAppi buildChNuAppiInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.chucknorris.io/jokes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ChNuAppi.class);
    }
}
