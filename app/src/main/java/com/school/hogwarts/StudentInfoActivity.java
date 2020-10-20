package com.school.hogwarts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.school.hogwarts.utilities.Config;

import java.util.Objects;

public class StudentInfoActivity extends AppCompatActivity {
    TextView tvHouse, tvSchool, tvMinistryOfMagic, tvOrderOfPheonix, tvDumbledoresArmy, tvDeathEater, tvBloodStatus, tvSpecies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        Config.TAG_NAME = bundle.getString(Config.TAG_NAME);
        toolBarLayout.setTitle(Config.TAG_NAME);

        initViews();

        Config.TAG_HOUSE_NAME = bundle.getString(Config.TAG_HOUSE_NAME);
        Config.TAG_SCHOOL = bundle.getString(Config.TAG_SCHOOL);
        Config.TAG_MINISTRY_OF_MAGIC = bundle.getString(Config.TAG_MINISTRY_OF_MAGIC);
        Config.TAG_ORDER_OF_THE_PHOENIX = bundle.getString(Config.TAG_ORDER_OF_THE_PHOENIX);
        Config.TAG_DUMBLEDORES_ARMY = bundle.getString(Config.TAG_DUMBLEDORES_ARMY);
        Config.TAG_DEATH_EATER = bundle.getString(Config.TAG_DEATH_EATER);
        Config.TAG_BLOOD_STATUS = bundle.getString(Config.TAG_BLOOD_STATUS);
        Config.TAG_SPECIES = bundle.getString(Config.TAG_SPECIES);

    }

    private void initViews() {
        tvHouse = findViewById(R.id.tvs_house);
        tvSchool = findViewById(R.id.tvs_school);
        tvMinistryOfMagic = findViewById(R.id.tvs_ministry);
        tvOrderOfPheonix = findViewById(R.id.tvs_pheonix);
        tvDumbledoresArmy = findViewById(R.id.tvs_dumbledoresarmy);
        tvDeathEater = findViewById(R.id.tvs_deatheater);
        tvBloodStatus = findViewById(R.id.tvs_bloodstatus);
        tvSpecies = findViewById(R.id.tvs_species);

        tvHouse.setText(Config.TAG_HOUSE_NAME);
        tvSchool.setText(Config.TAG_SCHOOL);
        tvMinistryOfMagic.setText(Config.TAG_MINISTRY_OF_MAGIC);
        tvOrderOfPheonix.setText(Config.TAG_ORDER_OF_THE_PHOENIX);
        tvDumbledoresArmy.setText(Config.TAG_DUMBLEDORES_ARMY);
        tvDeathEater.setText(Config.TAG_DEATH_EATER);
        tvBloodStatus.setText(Config.TAG_BLOOD_STATUS);
        tvSpecies.setText(Config.TAG_SPECIES);
    }
}