package com.shoppingcart;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<Integer> adapter ;
    private List<Integer> list;
    private ListView listView;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<Integer>();
        for (int i = 0; i < 18; i++) {
            list.add(0);
        }

        adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,list);
        //注册观察者
        adapter.registerDataSetObserver(sumObserver);

        text = (TextView) findViewById(R.id.text);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.set(i,2);
                //将列表项的0变为2 更新适配器，
                adapter.notifyDataSetChanged();
                //执行该方法后DataSetObserver观察者观察到
            }
        });


    }

    private DataSetObserver sumObserver = new DataSetObserver() {
        @Override
        public void onInvalidated() {
            super.onInvalidated();

        }

        @Override
        public void onChanged() {
            super.onChanged();
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum+=list.get(i);
            }
            text.setText("总金额:"+sum);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.unregisterDataSetObserver(sumObserver);
    }
}
