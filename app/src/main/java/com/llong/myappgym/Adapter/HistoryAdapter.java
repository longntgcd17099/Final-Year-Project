package com.llong.myappgym.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.llong.myappgym.Model.HistoryModel;
import com.llong.myappgym.Model.WorkOutModel;
import com.llong.myappgym.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HistoryAdapter extends BaseAdapter {
    private Context context;
    public ArrayList<HistoryModel> historyModels;

    public HistoryAdapter(Context context, ArrayList<HistoryModel> historyModels) {
        this.context = context;
        this.historyModels = historyModels;
    }

    @Override
    public int getCount() {
        return historyModels.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }
    public  class  ViewHodler{
        ImageView hinhanh;
        TextView txttile,txtdate,txtmota,txtgio;

    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_history,null);
            viewHodler=new ViewHodler();
            viewHodler.txtdate=convertView.findViewById(R.id.txtdate);
            viewHodler.txtgio=convertView.findViewById(R.id.txtgio);
            viewHodler.txtmota=convertView.findViewById(R.id.txtmota);
            viewHodler.txttile=convertView.findViewById(R.id.txttile);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.txttile.setText("Title : "+historyModels.get(position).getTitle());
        viewHodler.txtmota.setText("Content : "+historyModels.get(position).getNoidung());
        viewHodler.txtdate.setText(historyModels.get(position).getNgay());
        viewHodler.txtgio.setText("Timer Practie : "+historyModels.get(position).getThoigian());
        return convertView;
    }
}
