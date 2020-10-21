package com.school.hogwarts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.school.hogwarts.models.Color;
import com.school.hogwarts.models.Member;
import com.school.hogwarts.utilities.Config;

import java.util.ArrayList;

public class HouseInfoActivity extends AppCompatActivity {
    private TextView tvMascot, tvFounder, tvHeadOfhouse, tvHouseGhost;
    private ArrayList<String> arrayList, arrayListMembers;
    private ListView listViewColors, listViewMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_info);
        Bundle bundle = getIntent().getExtras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        intiViews();


        Config.TAG_HOUSE_NAME = bundle.getString(Config.TAG_HOUSE_NAME);
        toolBarLayout.setTitle(Config.TAG_HOUSE_NAME);
        Config.TAG_MASCOT = bundle.getString(Config.TAG_MASCOT);
        Config.TAG_HEAD_OF_HOUSE = bundle.getString(Config.TAG_HEAD_OF_HOUSE);
        Config.TAG_HOUSE_GHOST = bundle.getString(Config.TAG_HOUSE_GHOST);
        Config.TAG_FOUNDER = bundle.getString(Config.TAG_FOUNDER);


        ArrayList<Color> arrayListColors = this.getIntent().getParcelableArrayListExtra(Config.TAG_COLORS);
        ArrayList<Member> arrayListMember = this.getIntent().getParcelableArrayListExtra(Config.TAG_MEMBERS);


        for (int i = 0; i < arrayListMember.size(); i++) {
            arrayListMembers.add(arrayListMember.get(i).getMember());
        }

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.layout_member_header, listViewMembers, false);
        listViewMembers.addHeaderView(header, null, false);

        ArrayAdapter<String> membersAdapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, android.R.id.text1, arrayListMembers);
        listViewMembers.setAdapter(membersAdapter);

        for (int i = 0; i < arrayListColors.size(); i++) {
            arrayList.add(arrayListColors.get(i).getColor());
        }

        LayoutInflater colorsInflater = getLayoutInflater();
        ViewGroup colorsHeader = (ViewGroup) colorsInflater.inflate(R.layout.layout_color_header, listViewColors, false);
        listViewColors.addHeaderView(colorsHeader, null, false);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        listViewColors.setAdapter(adapter);
    }

    private void intiViews() {
        tvMascot = findViewById(R.id.tvmascot);
        tvHeadOfhouse = findViewById(R.id.tvheadOfhouse);
        tvHouseGhost = findViewById(R.id.tvhouseGhost);
        tvFounder = findViewById(R.id.tvfounder);
        listViewColors = findViewById(R.id.listview);
        listViewMembers = findViewById(R.id.Memberslistview);

        listViewMembers.setVisibility(View.GONE);

        arrayList = new ArrayList<>();
        arrayListMembers = new ArrayList<>();

        tvMascot.setText(Config.TAG_MASCOT);
        tvHeadOfhouse.setText(Config.TAG_HEAD_OF_HOUSE);
        tvHouseGhost.setText(Config.TAG_HOUSE_GHOST);
        tvFounder.setText(Config.TAG_FOUNDER);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(HouseInfoActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}