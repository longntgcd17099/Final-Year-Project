package com.llong.myappgym.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.llong.myappgym.Model.WorkOutModel;
import com.llong.myappgym.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class WorkOutBuildingAdapter  extends BaseAdapter {
    private Context context;
    public ArrayList<WorkOutModel> workOutModelArrayList;

    public WorkOutBuildingAdapter(Context context, ArrayList<WorkOutModel> workOutModelArrayList) {
        this.context = context;
        this.workOutModelArrayList = workOutModelArrayList;
    }

    @Override
    public int getCount() {
        return workOutModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    public  class  ViewHodler{
        ImageView hinhanh;
        TextView txttile,txtdate,txtmota;

    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_workout_building,null);
            viewHodler=new ViewHodler();
            viewHodler.hinhanh=convertView.findViewById(R.id.hinhanh);
            viewHodler.txtdate=convertView.findViewById(R.id.txtdate);
            viewHodler.txtmota=convertView.findViewById(R.id.txtmota);
            viewHodler.txttile=convertView.findViewById(R.id.txttile);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.txttile.setText(workOutModelArrayList.get(position).getName());
        viewHodler.txtmota.setText(workOutModelArrayList.get(position).getMota());
        Picasso.with(context).load(workOutModelArrayList.get(position).getHinhanh())
                .into(viewHodler.hinhanh);
        viewHodler.txtdate.setText(workOutModelArrayList.get(position).getNgay());
        return convertView;
    }
}
