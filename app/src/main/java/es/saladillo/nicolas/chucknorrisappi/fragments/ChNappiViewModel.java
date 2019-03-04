package es.saladillo.nicolas.chucknorrisappi.fragments;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import es.saladillo.nicolas.chucknorrisappi.data.remote.chNuAppi.ChNuAppiService;
import es.saladillo.nicolas.chucknorrisappi.data.remote.entity.RandomFactDTO;
import es.saladillo.nicolas.chucknorrisappi.data.remote.entity.RandomFactTranslationDTO;
import es.saladillo.nicolas.chucknorrisappi.data.remote.yandexApi.YandexApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChNappiViewModel extends ViewModel {
    private MutableLiveData<String> response = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();
    private MediatorLiveData<String> translatedResponse = new MediatorLiveData<>();

    public ChNappiViewModel() {
        translatedResponse.addSource(response,fact -> requestRandomTranslation(fact));
    }

    public void requestRandomFact() {
        Call<RandomFactDTO> call = ChNuAppiService.getInstance().getChNuAppi().getFact();
        call.enqueue(new Callback<RandomFactDTO>() {
            @Override
            public void onResponse(Call<RandomFactDTO> call, Response<RandomFactDTO> response) {

                if (response.isSuccessful() && response.body() != null) {
                    ChNappiViewModel.this.response.setValue(response.body().getValue());
                }
            }

            @Override
            public void onFailure(Call<RandomFactDTO> call, Throwable t) {
                error.setValue("Chuck is tired of your bullshit");
            }
        });
    }

    public void requestRandomTranslation(String textToTranslate) {
        String yandexApiKey = "trnsl.1.1.20190215T184742Z.eaafc76c2cd5dc9c.a3d384df81f1103f090f6761c311d57e6856bf2c";

        Call<RandomFactTranslationDTO> call = YandexApiService.getInstance().getYandexApi().getFactTranslation(yandexApiKey,textToTranslate,"en-es");
        call.enqueue(new Callback<RandomFactTranslationDTO>() {
            @Override
            public void onResponse(Call<RandomFactTranslationDTO> call, Response<RandomFactTranslationDTO> response) {
                String respuestaTraducida = "";
                if (response.isSuccessful() && response.body() != null) {
                    for (String traduccion: response.body().getText()) {
                        respuestaTraducida += " " + traduccion;
                    }
                    ChNappiViewModel.this.translatedResponse.setValue(respuestaTraducida);
                }
            }

            @Override
            public void onFailure(Call<RandomFactTranslationDTO> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<String> getResponse() {
        return response;
    }

    public void setResponse(MutableLiveData<String> response) {
        this.response = response;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public void setError(MutableLiveData<String> error) {
        this.error = error;
    }

    public MediatorLiveData<String> getTranslatedResponse() {
        return translatedResponse;
    }

    public void setTranslatedResponse(MediatorLiveData<String> translatedResponse) {
        this.translatedResponse = translatedResponse;
    }
}
