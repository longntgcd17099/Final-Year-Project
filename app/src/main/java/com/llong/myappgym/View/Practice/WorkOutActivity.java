package com.llong.myappgym.View.Practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.llong.myappgym.Adapter.WorkOutBuildingAdapter;
import com.llong.myappgym.Model.WorkOutModel;
import com.llong.myappgym.PreSenter.Interface.IWorkOutView;
import com.llong.myappgym.PreSenter.WorkOutPreSenter;
import com.llong.myappgym.R;

import java.util.ArrayList;

public class WorkOutActivity  extends AppCompatActivity
 implements IWorkOutView {
    private Toolbar toolbar;
    private ListView lv;
    private WorkOutBuildingAdapter adapter;
    private ArrayList<WorkOutModel> arrayList;
    private WorkOutPreSenter workOutPreSenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worko);
        InitWidget();
        Init();
    }

    private void Init() {
        Intent intent=getIntent();
        String categories=intent.getStringExtra("Categories");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" Back ");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        arrayList=new ArrayList<>();
        workOutPreSenter=new WorkOutPreSenter(this);
        workOutPreSenter.HandleReadDataWorkOut(categories);
    }

    private void InitWidget() {
        lv=findViewById(R.id.lv);
        toolbar=findViewById(R.id.toolbar);

    }

    @Override
    public void getDataWorkOut(String categories, String des, String link, String name, String ngay, String noidung, String hinhanh, String mota) {
        arrayList.add(new WorkOutModel(categories,des,link,name,ngay,noidung,hinhanh,mota));
        adapter=new WorkOutBuildingAdapter(this,arrayList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(WorkOutActivity.this,ContentWorkOutActivity.class);
                intent.putExtra("WorkOut",arrayList.get(position));
                startActivity(intent);
            }
        });
    }
}
