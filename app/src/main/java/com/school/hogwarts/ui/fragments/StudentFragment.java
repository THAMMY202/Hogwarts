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
import com.school.hogwarts.adapters.StudentAdapter;
import com.school.hogwarts.interfaces.GetDataService;
import com.school.hogwarts.models.Student;
import com.school.hogwarts.utilities.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentFragment extends Fragment {
    private LinearLayoutManager linearlayout;
    private RecyclerView recyclerView;
    private StudentAdapter studentViewAdapter;
    private ProgressDialog progressDialog;
    private TextView textViewNoResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_student, container, false);

        intiViews(view);
        getAllStudent();
        return view;
    }

    private void intiViews(View view) {
        textViewNoResult = view.findViewById(R.id.tvNoResults);
        progressDialog = new ProgressDialog(getActivity());
        recyclerView = view.findViewById(R.id.listStudents);
        linearlayout = new LinearLayoutManager(getActivity());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearlayout);
    }

    private void getAllStudent() {

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Student>> call = service.getAllStudents();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<Student> studentList) {

        if(studentList.size() > 0){
            studentViewAdapter = new StudentAdapter(getActivity(), studentList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(studentViewAdapter);
            textViewNoResult.setVisibility(View.GONE);
        }else {
            textViewNoResult.setVisibility(View.VISIBLE);
        }

    }
}