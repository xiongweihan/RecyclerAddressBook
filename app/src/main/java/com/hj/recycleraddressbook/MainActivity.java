package com.hj.recycleraddressbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvAddressBook;
    private AddressBookAdapter adapter;
    private List<PersonInfoBean> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        initData();
        rvAddressBook = findViewById(R.id.rv_address_book);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvAddressBook.setLayoutManager(manager);
        adapter = new AddressBookAdapter(this, list);
//        rvAddressBook.addItemDecoration(new SectionDecoration(MainActivity.this, new SectionDecoration.DecorationCallBack() {
//            //返回标记id (即每一项对应的标志性的字符串)
//            @Override
//            public String getGroupName(int position) {
//                if(list.get(position).getName() != null){
//                    return list.get(position).getName();
//                }
//                return "#";
//            }
//        }));

        rvAddressBook.addItemDecoration(new StarDecoration(this, new StarDecoration.callBack() {
            @Override
            public String getGroupName(int position) {
                if (!TextUtils.isEmpty(list.get(position).getName())) {
                    return list.get(position).getName();
                }
                return "";
            }
        }));
        rvAddressBook.setAdapter(adapter);

    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            PersonInfoBean bean = new PersonInfoBean("张三", "17", "001", "2020-12-21");
            list.add(bean);
        }
        for (int i = 0; i < 10; i++) {
            PersonInfoBean bean2 = new PersonInfoBean("李四", "17", "001", "2020-12-21");
            list.add(bean2);
        }
        for (int i = 0; i < 10; i++) {
            PersonInfoBean bean3 = new PersonInfoBean("王四", "17", "001", "2020-12-21");
            list.add(bean3);
        }
        for (int i = 0; i < 10; i++) {
            PersonInfoBean bean4 = new PersonInfoBean("曾四", "17", "001", "2020-12-21");
            list.add(bean4);
        }
    }
}