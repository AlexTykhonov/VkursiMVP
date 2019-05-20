package com.tae.vkursimvp.listcurrency;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tae.vkursimvp.PojoVal;
import com.tae.vkursimvp.PostsAdapter;
import com.tae.vkursimvp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListCurrencyContract.View{

    RecyclerView recyclerView;
    PostsAdapter postsAdapter;
    ArrayList vallist = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListCurrencyContract.Presenter presenter = new MainPresenter(this);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postsAdapter = new PostsAdapter(this);

        recyclerView.setAdapter(postsAdapter);
    }

    @Override
    public void displayCurrency(ArrayList<PojoVal> pojoNbu) {
        vallist.clear();
        vallist.addAll(pojoNbu);
        postsAdapter.setData(vallist); // вот строку добавил
        postsAdapter.notifyDataSetChanged();
        System.out.println("________________VIEW___________---------------"+pojoNbu);

    }
}
