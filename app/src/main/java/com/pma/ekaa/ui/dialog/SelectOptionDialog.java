package com.pma.ekaa.ui.dialog;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
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

    private static String LIST_DATA = "list_data";
    private static String IS_SEARCHABLE = "is_searchable";
    private static onListenerInterface mListener;

    private ArrayList<Data> listData;
    private Boolean isSearchable;


    public SelectOptionDialog() {
        // Empty constructor required for DialogFragment
    }

    public static SelectOptionDialog newInstance(String listData, Boolean isSearchable, onListenerInterface callback) {
        mListener = callback;
        SelectOptionDialog frag = new SelectOptionDialog();
        Bundle args = new Bundle();
        args.putString(LIST_DATA, listData);
        args.putBoolean(IS_SEARCHABLE, isSearchable);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Material_Light_Dialog_Alert);
        if (getArguments() != null) {
            listData = new Gson().fromJson(getArguments().getString(LIST_DATA), new TypeToken<List<Data>>(){}.getType());
            isSearchable = getArguments().getBoolean(IS_SEARCHABLE);
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
        SearchView searchOption = view.findViewById(R.id.searchOption);

        if(isSearchable){
            searchOption.setVisibility(View.VISIBLE);
        } else {
            searchOption.setVisibility(View.GONE);
        }

        SelectOptionAdapter itemAdapter = new SelectOptionAdapter(listData, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(itemAdapter);
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
