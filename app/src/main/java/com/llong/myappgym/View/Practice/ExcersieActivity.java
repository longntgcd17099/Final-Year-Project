package com.llong.myappgym.View.Practice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.llong.myappgym.Adapter.ExcersieApdater;
import com.llong.myappgym.Model.ExcersieModel;
import com.llong.myappgym.PreSenter.ExcersiePreSenter;
import com.llong.myappgym.PreSenter.Interface.ExcersieView;
import com.llong.myappgym.R;

import java.util.ArrayList;

public class ExcersieActivity  extends AppCompatActivity implements ExcersieView {
    private ExcersiePreSenter excersiePreSenter;
    private Intent intent;
    private String KEY;
    private GridView gv;
    private ArrayList<ExcersieModel> arrayList;
    private ExcersieApdater apdater;
    private Toolbar toolbar;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excersie);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Init() {
        setSupportActionBar(toolbar);

        arrayList=new ArrayList<>();
        intent=getIntent();
        KEY=intent.getStringExtra("Categories");
        excersiePreSenter=new ExcersiePreSenter(this);
        excersiePreSenter.HandleReadDataExcerise(KEY);
        getSupportActionBar().setTitle(KEY);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void InitWidget() {
        gv=findViewById(R.id.gv);
        toolbar=findViewById(R.id.toolbar);

    }

    @Override
    public void getDataExcerise(String hinhanh, String link, String title, String noidung,String categories) {
        arrayList.add(new ExcersieModel(hinhanh,link,title,noidung,categories));
        apdater=new ExcersieApdater(arrayList,this);
        gv.setAdapter(apdater);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ExcersieActivity.this,ContentExcersiActivity.class);
                 intent.putExtra("Excersie",arrayList.get(position));
                 startActivity(intent);
            }
        });
    }
}
