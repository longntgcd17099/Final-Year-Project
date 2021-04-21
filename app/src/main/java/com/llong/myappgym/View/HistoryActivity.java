package com.llong.myappgym.View;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import com.llong.myappgym.Adapter.HistoryAdapter;
import com.llong.myappgym.Model.HistoryModel;
import com.llong.myappgym.PreSenter.Interface.UserView;
import com.llong.myappgym.PreSenter.UserPreSenter;
import com.llong.myappgym.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity  extends AppCompatActivity
 implements UserView {
    private AppCompatSpinner appCompatSpinner;
    private Toolbar toolbar;
    private ListView lv;
    private ArrayList<HistoryModel> arrayList;
    private List<String> list;
    private ArrayAdapter adapter;
    private HistoryAdapter historyAdapter;
    private UserPreSenter userPreSenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        InitWisget();
        Init();
    }

    private void Init() {
        userPreSenter=new UserPreSenter(this);
        arrayList=new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("History");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list=new ArrayList<>();
        list.add("Chọn Mục");
        list.add("Excersie");
        list.add("WorkOut");
        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,list);
        appCompatSpinner.setAdapter(adapter);
        appCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(arrayList.size()>0){
                    arrayList.clear();
                }
                if(position>0){
                    userPreSenter.HandlegetDataHistory(list.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void InitWisget() {
        appCompatSpinner=findViewById(R.id.spinner);
        toolbar=findViewById(R.id.toolbar);
        lv=findViewById(R.id.lv);
    }

    @Override
    public void OnValid() {

    }

    @Override
    public void OnSucess() {

    }

    @Override
    public void OnFail() {

    }

    @Override
    public void OnEmailSucess() {

    }

    @Override
    public void getDataHistory(String title, String ngay, String thoigian, String noidung) {
           arrayList.add(new HistoryModel(title,ngay,noidung,thoigian));
           historyAdapter=new HistoryAdapter(this,arrayList);
           lv.setAdapter(historyAdapter);
    }
}
