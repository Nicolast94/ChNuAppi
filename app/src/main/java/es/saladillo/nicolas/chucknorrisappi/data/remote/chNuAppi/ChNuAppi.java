package es.saladillo.nicolas.chucknorrisappi.data.remote.chNuAppi;

import es.saladillo.nicolas.chucknorrisappi.data.remote.entity.RandomFactDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChNuAppi {

    @GET("random")
    Call<RandomFactDTO> getFact();

}
