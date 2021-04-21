package com.llong.myappgym.View.FragMent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.llong.myappgym.R;
import com.llong.myappgym.View.Practice.ExcersieActivity;

public class FragMent_Excersie  extends Fragment
implements  View.OnClickListener{
    View view;
    private CardView c1,c2,c3,c4,c5,c6,c7,c8,c9;
    private Intent intent;
    private String Categories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_excersie,container,false);
        InitWidget();
        Init();
        HandleEvents();
        return  view;
    }

    private void HandleEvents() {
        c1.setOnClickListener(this);
    }

    private void Init() {
        intent=new Intent(getContext(), ExcersieActivity.class);

    }

    private void InitWidget() {
        c1=view.findViewById(R.id.c1);
        c2=view.findViewById(R.id.c2);
        c3=view.findViewById(R.id.c3);
        c4=view.findViewById(R.id.c4);
        c5=view.findViewById(R.id.c5);
        c6=view.findViewById(R.id.c6);
        c7=view.findViewById(R.id.c7);
        c8=view.findViewById(R.id.c8);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.c1: Categories="abs";break;

        }
        intent.putExtra("Categories",Categories);
        startActivity(intent);
    }
}
