package com.pma.ekaa.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pma.ekaa.R;
import com.pma.ekaa.data.models.Data;

import java.util.ArrayList;
import java.util.List;

public class SelectOptionDialog extends DialogFragment implements SelectOptionAdapter.onAdapterListener {

    private static final String LIST_DATA = "list_data";
    private static onListenerInterface mListener;

    private ArrayList<Data> listData;
    private SelectOptionAdapter mAdapter;

    public SelectOptionDialog() {
        // Empty constructor required for DialogFragment
    }

    public static SelectOptionDialog newInstance(String listData, onListenerInterface callback) {
        mListener = callback;
        SelectOptionDialog frag = new SelectOptionDialog();
        Bundle args = new Bundle();
        args.putString(LIST_DATA, listData);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Material_Light_Dialog_Alert);
        if (getArguments() != null) {
            listData = new Gson().fromJson(getArguments().getString(LIST_DATA), new TypeToken<List<Data>>(){}.getType());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setCancelable(false);
        return inflater.inflate(R.layout.layout_option_dialog, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerOption);
        SearchView searchBox = view.findViewById(R.id.searchBox);

        mAdapter = new SelectOptionAdapter(listData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setAdapter(mAdapter);


        searchBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });


        view.findViewById(R.id.textCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    @Override
    public void ItemSelect(Data data) {
        dismiss();
        mListener.optionSelect(data);
    }

    public interface onListenerInterface{
        void optionSelect(Data data);
    }
}
