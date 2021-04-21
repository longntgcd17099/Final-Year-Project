package com.llong.myappgym.View.FragMent;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.llong.myappgym.Adapter.WorkOutAdapter;
import com.llong.myappgym.R;
import com.llong.myappgym.View.Practice.ExcersieActivity;
import com.llong.myappgym.View.Practice.WorkOutActivity;

import java.util.ArrayList;

public class FragMent_WorkOut extends Fragment
implements AdapterView.OnItemClickListener {
    View view;
    private GridView gv;
    private Intent intent;
    private String Works;
    private WorkOutAdapter workOutAdapter;
    private ArrayList<String> arrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_workout,container,false);
        InitWidget();
        Init();
        HandleEvents();
        return  view;
    }

    private void HandleEvents() {
         arrayList=new ArrayList<>();
         arrayList.add("Body BuilDing : 2 Days Per Week");
         arrayList.add("Body BuilDing : 3 Days Per Week");
         arrayList.add("Body BuilDing : 4 Days Per Week");
         arrayList.add("Fitness : 2 Days Per Week");
         arrayList.add("Fitness : 3 Days Per Week");
         arrayList.add("Fitness : 4 Days Per Week");
         arrayList.add("Powerlifting : 2 Days Per Week");
         arrayList.add("Powerlifting : 3 Days Per Week");
         arrayList.add("Powerlifting : 4 Days Per Week");
         workOutAdapter=new WorkOutAdapter(arrayList,getContext());
         gv.setAdapter(workOutAdapter);
        gv.setOnItemClickListener(this);

    }

    private void Init() {
        intent=new Intent(getContext(), ExcersieActivity.class);

    }

    private void InitWidget() {
        gv=view.findViewById(R.id.gv);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent=new Intent(getContext(), WorkOutActivity.class);
            String key="";
            switch (position){
                case 0:key="BodyTwo";break;
                case 1:key="BodyThree";break;
                case 2:key="BodyFour";break;
                case 3:key="FitTwo";break;
                case 4:key="FitThree";break;
                case 5:key="FitFour";break;
                case 6:key="PowerTwo";break;
                case 7:key="PowerThree";break;
                case 8:key="PowerFour";break;

            }
            intent.putExtra("Categories",key);
            getActivity().startActivity(intent);
    }
}
