package com.school.hogwarts.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.school.hogwarts.HouseInfoActivity;
import com.school.hogwarts.R;
import com.school.hogwarts.models.Color;
import com.school.hogwarts.models.House;
import com.school.hogwarts.models.Member;
import com.school.hogwarts.utilities.Config;

import java.util.ArrayList;
import java.util.List;


public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.HouseviewHolder>{
    public List<House> list ;
    public ArrayList<Color> listColorsModel = new ArrayList<>() ;
    public ArrayList<Member> listMembersModel = new ArrayList<>() ;
    private Context context;


    public HouseAdapter(Context context, List<House> list) {
        this.list = list;
        this.context = context;

    }
    @Override
    public HouseviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.house_cardview,null);
        HouseviewHolder viewHolder = new HouseviewHolder(v);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(final HouseviewHolder holder, final int position) {


        holder.tvHouseName.setText(list.get(position).getName());

        holder.tvHouseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i =0; i< list.get(position).getColors().size();i++ ){
                    Color colorsModel = new Color();
                    colorsModel.setColor(list.get(position).getColors().get(i));
                    listColorsModel.add(colorsModel);
                }

                for(int i = 0; i <list.get(position).getValues().size(); i++){
                    Member membersModel = new Member();
                    membersModel.setMember(list.get(position).getValues().get(i));
                    listMembersModel.add(membersModel);
                }


                Intent i = new Intent(context, HouseInfoActivity.class);
                i.putExtra(Config.TAG_HOUSE_NAME,list.get(position).getName());
                i.putExtra(Config.TAG_MASCOT,list.get(position).getMascot());
                i.putExtra(Config.TAG_HEAD_OF_HOUSE,list.get(position).getHeadOfHouse());
                i.putExtra(Config.TAG_HOUSE_GHOST,list.get(position).getHouseGhost());
                i.putExtra(Config.TAG_FOUNDER,list.get(position).getFounder());
                i.putExtra(Config.TAG_COLORS, listColorsModel);
                i.putExtra(Config.TAG_MEMBERS, listMembersModel);
                context.startActivity(i);

            }
        });
    }
    @Override
    public int getItemCount() {
        return this.list.size();
    }

        public class HouseviewHolder extends RecyclerView.ViewHolder{

            TextView tvHouseName;

            public HouseviewHolder(@NonNull View itemView) {
                super(itemView);
                tvHouseName = itemView.findViewById(R.id.tvhouse_name);
            }
        }
}
