package com.school.hogwarts.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.school.hogwarts.R;
import com.school.hogwarts.adapters.SpellsAdapter;
import com.school.hogwarts.interfaces.GetDataService;
import com.school.hogwarts.models.Spells;
import com.school.hogwarts.utilities.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SpellFragment extends Fragment {
    private LinearLayoutManager linearlayout;
    private RecyclerView recyclerView;
    private SpellsAdapter spellsAdapter;
    private ProgressDialog progressDialog;
    private TextView textViewNoResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_spell, container, false);

        intiViews(view);
        getAllSpell();
        return view;
    }

    private void intiViews(View view) {
        textViewNoResult = view.findViewById(R.id.tvNoResults);
        progressDialog = new ProgressDialog(getActivity());
        recyclerView = view.findViewById(R.id.listSpells);
        linearlayout = new LinearLayoutManager(getActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);
    }

    private void getAllSpell() {

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Spells>> call = service.getAllSpells();
        call.enqueue(new Callback<List<Spells>>() {
            @Override
            public void onResponse(Call<List<Spells>> call, Response<List<Spells>> response) {
                progressDialog.dismiss();
                textViewNoResult.setVisibility(View.GONE);
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Spells>> call, Throwable t) {
                progressDialog.dismiss();
                textViewNoResult.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void generateDataList(List<Spells> spellsList) {

        if (spellsList.size() > 0) {
            spellsAdapter = new SpellsAdapter(getActivity(), spellsList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(spellsAdapter);
            textViewNoResult.setVisibility(View.GONE);
        } else {
            textViewNoResult.setVisibility(View.VISIBLE);
        }

    }
}