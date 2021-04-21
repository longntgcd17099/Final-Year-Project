package com.llong.myappgym.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.llong.myappgym.Model.ExcersieModel;
import com.llong.myappgym.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExcersieApdater  extends BaseAdapter {
    private ArrayList<ExcersieModel> excersieModelArrayList;
    private Context context;

    public ExcersieApdater(ArrayList<ExcersieModel> excersieModelArrayList, Context context) {
        this.excersieModelArrayList = excersieModelArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return excersieModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public  class ViewHodler{
        ImageView hinhanh;
        TextView title;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_excersie,null);
            viewHodler=new ViewHodler();
            viewHodler.hinhanh=convertView.findViewById(R.id.hinhanh);
            viewHodler.title=convertView.findViewById(R.id.txttile);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.title.setText(excersieModelArrayList.get(position).getTitle());
        Picasso.with(context).load(excersieModelArrayList.get(position).getHinhanh()).into(viewHodler.hinhanh);
        return convertView;
    }
}
