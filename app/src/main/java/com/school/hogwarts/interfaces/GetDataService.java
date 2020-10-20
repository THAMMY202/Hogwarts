package com.school.hogwarts.interfaces;

import com.school.hogwarts.models.*;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("houses/?key=$2a$10$1JEnmtEF417yBaFZcr51qukRjaKv8d5toEG5DKP/IUZWIVwfsaF7y")
    Call<List<House>> getAllHouses();

    @GET("spells/?key=$2a$10$1JEnmtEF417yBaFZcr51qukRjaKv8d5toEG5DKP/IUZWIVwfsaF7y")
    Call<List<Spells>> getAllSpells();

    @GET("characters/?key=$2a$10$1JEnmtEF417yBaFZcr51qukRjaKv8d5toEG5DKP/IUZWIVwfsaF7y")
    Call<List<Student>> getAllStudents();
}
