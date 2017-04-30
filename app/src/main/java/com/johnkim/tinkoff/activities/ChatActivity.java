package com.johnkim.tinkoff.activities;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Loader;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.johnkim.tinkoff.adapters.DialogAdapter;
import com.johnkim.tinkoff.api.DialogItem;
import com.johnkim.tinkoff.R;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<DialogItem>> {

    RecyclerView recyclerView;
    DialogAdapter adapter;

    ArrayList<DialogItem> messages;
    ProgressDialog progress;
    Button send;
    AppCompatEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        showLoader();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getLoaderManager().initLoader(0, null, this);

        editText = (AppCompatEditText)findViewById(R.id.edit_text_message);

        send = (AppCompatButton)findViewById(R.id.btn_send_message);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogItem messageItem = new DialogItem(editText.getText().toString(), 0, new Date());
                messageItem.save();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        messages.add(messageItem);
                        adapter.notifyDataSetChanged();
                    }
                }, 50);

                editText.getText().clear();
            }
        });
    }

    private void initRecyclerView(ArrayList<DialogItem> items) {
        recyclerView = (RecyclerView) findViewById(R.id.dialog_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        messages = items;
        adapter = new DialogAdapter(messages);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    private ArrayList<DialogItem> createDataset() {
            return (ArrayList) SQLite.select().from(DialogItem.class).queryList();
    }

    @Override
    public Loader<ArrayList<DialogItem>> onCreateLoader(int id, Bundle args) {


        Loader<ArrayList<DialogItem>> mLoader = new Loader<ArrayList<DialogItem>>(this){
            @Override
            protected void onStartLoading() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        }catch (InterruptedException ex){
                            ex.printStackTrace();
                        }
                        deliverResult(createDataset());
                    }
                }).start();

            }

            @Override
            protected void onStopLoading() {

            }

        };
        return mLoader;

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<DialogItem>> loader, final ArrayList<DialogItem> data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecyclerView(data);
                hideLoader();
            }
        });

    }



    @Override
    public void onLoaderReset(Loader<ArrayList<DialogItem>> loader) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showLoader(){
        progress = new ProgressDialog(this);
        progress.setTitle("Загружаем");
        progress.setMessage("Сообщения в диалоге");
        progress.setCancelable(false);
        progress.show();
    }

    public void hideLoader(){
        progress.dismiss();
    }
}

