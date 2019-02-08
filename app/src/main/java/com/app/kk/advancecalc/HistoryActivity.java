package com.app.kk.advancecalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.app.kk.advancecalc.Database.DBHelper;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView lv;
    private DBHelper dbHelper;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    private String calcName="";
    private String []EmptyList={"There is  no history yet"};
    SharedPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedpref = new SharedPref(this);
        if(sharedpref.loadNightModeState()==true) {
            setTheme(R.style.DarkTheme_Bar);
        }
        else  setTheme(R.style.AppTheme_Bar);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lv=(ListView)findViewById(R.id.listView);
        final Button deleteHistory = (Button) findViewById(R.id.deleteHistory);
        deleteHistory.setBackgroundColor(getColor(R.color.primaryColor2));
        deleteHistory.setTextColor(getColor(R.color.White));

        dbHelper=new DBHelper(this);
        calcName=getIntent().getStringExtra("calcName");
        list=dbHelper.showHistory(calcName);
        if(!list.isEmpty()) {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            deleteHistory.setBackgroundColor(getColor(R.color.primaryColor2));
            deleteHistory.setTextColor(getColor(R.color.White));
            deleteHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        dbHelper.deleteRecords(calcName);
                        adapter = new ArrayAdapter<String>(HistoryActivity.this, android.R.layout.simple_list_item_1, EmptyList);
                        lv.setAdapter(adapter);
                        deleteHistory.setBackgroundColor(getColor(R.color.Grey));
                        deleteHistory.setTextColor(getColor(R.color.White));
                        Toast.makeText(HistoryActivity.this,"History Cleared", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, EmptyList);
            deleteHistory.setBackgroundColor(getColor(R.color.Grey));
            deleteHistory.setTextColor(getColor(R.color.White));
            deleteHistory.setEnabled(false);
        }
        lv.setAdapter(adapter);


    }

}
