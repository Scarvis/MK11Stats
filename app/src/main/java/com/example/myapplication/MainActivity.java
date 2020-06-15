package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapters.DatabaseAdapter;
import com.example.myapplication.Adapters.KombatAdapter;
import com.example.myapplication.MKCorePack.DatabaseHelper;
import com.example.myapplication.MKCorePack.JSONHelper;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.MKCorePack.Variation;
import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.ViewModels.HomeFragment;
import com.example.myapplication.ViewModels.KombatsListViewModel;
import com.example.myapplication.ViewModels.MKViewModel;
import com.example.myapplication.ViewModels.ProfileViewModel;
//import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MKViewModel mkViewModel;
    private static final int REQUEST_PERMISSION_WRITE = 1001;
    private ArrayList<Kombat> kombatArrayList = new ArrayList<Kombat>();
    private KombatAdapter arrayAdapter;
    private ListView testV;
    private DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mkViewModel = new MKViewModel(this);
        Initialize();
        testV = findViewById(R.id.KombatListViewId);
        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
//        db = databaseHelper.getReadableDatabase();
//        databaseHelper.reset(db);
//        Cursor cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
//        Log.d("work pls", "onResume: " + cursor.getCount());
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(this);
        databaseAdapter.open();

        ArrayList<Kombat> kombats = databaseAdapter.getKombats();
        arrayAdapter = new KombatAdapter(this, R.layout.kombat_list_item, kombats);
        testV.setAdapter(arrayAdapter);
        databaseAdapter.close();
        mkViewModel.updateKombatsList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_view_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        TextView tv = findViewById(R.id.KombatTVid);
        tv.setText(item.getTitle());
        if (item.getTitle().toString().equals("kombats")) {
            Intent intent = new Intent(this, ActivityTwo.class);
            intent.putExtra(KombatsListViewModel.class.getSimpleName(), mkViewModel.getKombatsListViewModel());
            startActivity(intent);
        } else if (item.getTitle().toString().equals("add new kombat")) {
            Intent intent = new Intent(this, activity_add_kombat.class);
            intent.putExtra(ArrayList.class.getSimpleName(), mkViewModel.getCharactersList());
            startActivity(intent);
        } else if (item.getTitle().toString().equals("profile")) {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra(ProfileViewModel.class.getSimpleName(), mkViewModel.getProfileViewModel());
            intent.putExtra(KombatsListViewModel.class.getSimpleName(), mkViewModel.getKombatsListViewModel());
            startActivity(intent);
        } else if (item.getTitle().toString().equals("news")) {
//            Intent intent = new Intent(this, NewsActivity.class);
//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    public void addNewKombat(View view) {
        //mkViewModel.addNewKombat();
    }

    private void Initialize() {
//        kombatArrayList.add(new Kombat(scorp, sub, 0));
//        kombatArrayList.add(new Kombat(scorp, sub, 1));
//        kombatArrayList.add(new Kombat(sub, scorp, 0));
//        kombatArrayList = JSONHelper.importFromJSON(this);
//        mkViewModel.setKombatsList(kombatArrayList);


//        List<Kombat> res = JSONHelper.importFromJSON(this);
//        for (Kombat ks : res){
//            kombatArrayList.add(ks);
//        }
    }
}
