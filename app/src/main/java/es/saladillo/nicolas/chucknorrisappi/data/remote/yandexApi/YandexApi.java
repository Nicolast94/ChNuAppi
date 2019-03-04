package es.saladillo.nicolas.chucknorrisappi.data.remote.yandexApi;

import es.saladillo.nicolas.chucknorrisappi.data.remote.entity.RandomFactTranslationDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface YandexApi {
    @GET("translate")
    Call<RandomFactTranslationDTO> getFactTranslation(@Query("key") String yandexApiKey, @Query("text") String textToTranslate,@Query("lang") String lang);
}
