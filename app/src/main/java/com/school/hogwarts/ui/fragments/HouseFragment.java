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
import com.school.hogwarts.adapters.HouseAdapter;
import com.school.hogwarts.interfaces.GetDataService;
import com.school.hogwarts.models.House;
import com.school.hogwarts.utilities.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class HouseFragment extends Fragment {
    private LinearLayoutManager linearlayout;
    private RecyclerView recyclerView;
    private HouseAdapter adapter;
    private ProgressDialog progressDialog;
    private TextView textViewNoResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_house, container, false);
        intiViews(view);

        getAllHouses();
        return view;
    }

    private void intiViews(View view) {
        textViewNoResult = view.findViewById(R.id.tvNoResults);
        progressDialog = new ProgressDialog(getActivity());
        recyclerView = view.findViewById(R.id.listHouses);
        linearlayout = new LinearLayoutManager(getActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);
    }

    private void getAllHouses() {

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<House>> call = service.getAllHouses();
        call.enqueue(new Callback<List<House>>() {
            @Override
            public void onResponse(Call<List<House>> call, retrofit2.Response<List<House>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<House>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<House> houseList) {

        if(houseList.size() >0){
            adapter = new HouseAdapter(getActivity(), houseList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            textViewNoResult.setVisibility(View.GONE);

        } else {
            textViewNoResult.setVisibility(View.VISIBLE);
        }

    }
}