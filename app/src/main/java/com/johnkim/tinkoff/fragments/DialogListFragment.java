package com.johnkim.tinkoff.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.johnkim.tinkoff.adapters.DialogListAdapter;
import com.johnkim.tinkoff.api.DialogListItem;
import com.johnkim.tinkoff.R;
import com.johnkim.tinkoff.activities.ChatActivity;
import com.johnkim.tinkoff.util.OnItemClickListener;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by john on 2017. 3. 30..
 */

public class DialogListFragment extends Fragment {
    RecyclerView recyclerView;
    DialogListAdapter adapter;
    RelativeLayout layout;
    FloatingActionButton fab;

    private List<DialogListItem> dialogs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layout = (RelativeLayout) inflater.inflate(R.layout.fragment_list, container, false);

        initRecyclerView();

        return layout;
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) layout.findViewById(R.id.dialog_list_recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        dialogs = createDataset();
        adapter = new DialogListAdapter(dialogs, new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        fab = (FloatingActionButton) layout.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogListItem item = new DialogListItem("Sample", "Simple", "Photo", new Date());
                item.save();
                adapter.add(item);
            }
        });
    }

    private List<DialogListItem> createDataset() {
        dialogs = SQLite.select().from(DialogListItem.class).queryList();
        return dialogs;
    }

}
