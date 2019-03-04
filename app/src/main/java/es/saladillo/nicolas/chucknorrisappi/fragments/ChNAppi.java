package es.saladillo.nicolas.chucknorrisappi.fragments;

import androidx.core.view.ViewCompat;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import es.saladillo.nicolas.chucknorrisappi.R;

public class ChNAppi extends Fragment {

    private ChNappiViewModel vm;
    private TextView lblFact;
    private Button btnRandomFact;
    private TextView lblFactTranslated;
    private ImageView imgChuckNorris;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ch_nappi_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = ViewModelProviders.of(this).get(ChNappiViewModel.class);
        setupViews();
    }

    private void setupViews() {
        lblFact = ViewCompat.requireViewById(getView(),R.id.lblFact);
        lblFactTranslated = ViewCompat.requireViewById(getView(),R.id.lblFactTranslated);
        btnRandomFact = ViewCompat.requireViewById(getView(),R.id.btnRandomFact);
        imgChuckNorris = ViewCompat.requireViewById(getView(),R.id.imgChuckNorris);

        vm.getResponse().observe(requireActivity(), respuesta -> lblFact.setText(respuesta));
        vm.getTranslatedResponse().observe(requireActivity(), respuesta -> lblFactTranslated.setText(respuesta));
        vm.getError().observe(requireActivity(), errorMsg -> mostrarToas(errorMsg));
        btnRandomFact.setOnClickListener(v -> vm.requestRandomFact());
        Picasso.with(getContext()).load("https://assets.chucknorris.host/img/chucknorris_logo_coloured_small@2x.png").into(imgChuckNorris);
    }

    private void mostrarToas(String msgError){
        Toast.makeText(getContext(),msgError,Toast.LENGTH_SHORT).show();
    }

}
