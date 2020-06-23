package com.example.myapplication.ViewModels;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapters.KombatAdapter;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class KombatsFragment extends Fragment {
    private ArrayList<Kombat> kombatArrayList;

    public KombatsFragment(ArrayList<Kombat> kombatArrayList) {
        this.kombatArrayList = kombatArrayList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_two, container, false);
        TextView textView = view.findViewById(R.id.kombats_countKombatsTextView);
        textView.setText((kombatArrayList.size() + " Kombats"));
        initializeSpinner(view);
        ListView kombatsListView = view.findViewById(R.id.DKombatListViewId);
        KombatAdapter kombatAdapter = new KombatAdapter(view.getContext(), R.layout.kombat_list_item, kombatArrayList);
        kombatAdapter.setColor(true);
        kombatsListView.setAdapter(kombatAdapter);
        return view;
    }

    private void initializeSpinner(View view) {
        Spinner spinner = view.findViewById(R.id.kombats_sortParametersSpinner);
        String [] options = {
                "By Date",
                "By UnDate"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.kombats_options_sort_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
