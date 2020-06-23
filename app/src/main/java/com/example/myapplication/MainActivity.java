package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toolbar;

import com.example.myapplication.Adapters.DatabaseAdapter;
import com.example.myapplication.Adapters.KombatAdapter;
import com.example.myapplication.MKCorePack.DatabaseHelper;
import com.example.myapplication.MKCorePack.JSONHelper;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.MKCorePack.Player;
import com.example.myapplication.MKCorePack.Variation;
import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.ViewModels.HomeFragment;
import com.example.myapplication.ViewModels.KombatsFragment;
import com.example.myapplication.ViewModels.KombatsListViewModel;
import com.example.myapplication.ViewModels.MKViewModel;
import com.example.myapplication.ViewModels.ProfileFragment;
import com.example.myapplication.ViewModels.ProfileViewModel;
import com.example.myapplication.ViewModels.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MKViewModel mkViewModel;
    private static final int REQUEST_PERMISSION_WRITE = 1001;
    private ArrayList<Kombat> kombatArrayList = new ArrayList<Kombat>();
    private Toolbar toolbar;
    private KombatAdapter arrayAdapter;
    private ListView testV;
    //#Test
//    private DatabaseHelper databaseHelper;
//    private SQLiteDatabase db;
    private boolean permissionGranted;
    private boolean downloadCharacters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mkViewModel = new MKViewModel(this);
        Initialize();
        //testV = findViewById(R.id.KombatListViewId);
        //databaseHelper = new DatabaseHelper(getApplicationContext());
        openText(testV);
        toolbar = findViewById(R.id.toolbarId);
        toolbar.setTitle("News");
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new HomeFragment()).commit();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorBlack)); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.statusBar)); //status bar or the time bar at the top
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
//        db = databaseHelper.getReadableDatabase();
//        databaseHelper.reset(db);
//        Cursor cursor = db.rawQuery("select * from " + DatabaseHelper.TABLE, null);
//        Log.d("work pls", "onResume: " + cursor.getCount());
        addNewKombat();
//        ArrayList<Kombat> kombats = mkViewModel.getKombatsList();
//        arrayAdapter = new KombatAdapter(this, R.layout.kombat_list_item, kombats);
//        testV.setAdapter(arrayAdapter);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    boolean isAddKombat = false;
                    switch (item.getItemId()) {
                        case R.id.bottom_navigation_item_news:
                            selectedFragment = new HomeFragment();
                            toolbar.setTitle("News");
                            break;
                        case R.id.bottom_navigation_item_kombats:
                            selectedFragment = new KombatsFragment(mkViewModel.getKombatsList());
                            toolbar.setTitle("Kombats");
                            break;
                        case R.id.bottom_navigation_item_add_new_kombat:
                            isAddKombat = true;
                            break;
                        case R.id.bottom_navigation_item_profile:
                            selectedFragment = new ProfileFragment(mkViewModel.getPlayer());
                            toolbar.setTitle("Profile");
                            break;
                        case R.id.bottom_navigation_item_settings:
                            selectedFragment = new SettingsFragment(mkViewModel.getPlayer());
                            toolbar.setTitle("Settings");
                            break;
                    }
                    if (isAddKombat) {
                        addKombat();
                        return false;
                    }
                    if (selectedFragment == null) return false;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                            selectedFragment).commit();
                    return true;
                }
            };

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.bottom_navigation_view_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        TextView tv = findViewById(R.id.KombatTVid);
//        tv.setText(item.getTitle());
//        if (item.getTitle().toString().equals("kombats")) {
//            Intent intent = new Intent(this, ActivityTwo.class);
//            intent.putExtra(KombatsListViewModel.class.getSimpleName(), mkViewModel.getKombatsListViewModel());
//            startActivity(intent);
//        } else if (item.getTitle().toString().equals("add new kombat")) {
//            Intent intent = new Intent(this, activity_add_kombat.class);
//            intent.putExtra(ArrayList.class.getSimpleName(), mkViewModel.getCharactersList());
//            startActivity(intent);
//        } else if (item.getTitle().toString().equals("profile")) {
//            Intent intent = new Intent(this, ProfileActivity.class);
//            intent.putExtra(ProfileViewModel.class.getSimpleName(), mkViewModel.getProfileViewModel());
//            intent.putExtra(KombatsListViewModel.class.getSimpleName(), mkViewModel.getKombatsListViewModel());
//            startActivity(intent);
//        } else if (item.getTitle().toString().equals("news")) {
////            Intent intent = new Intent(this, NewsActivity.class);
////            startActivity(intent);
//        }
//        return super.onOptionsItemSelected(item);
//    }
    private void addKombat() {
        Intent intent = new Intent(this, activity_add_kombat.class);
        intent.putExtra(ArrayList.class.getSimpleName(), mkViewModel.getCharactersList());
        startActivity(intent);
    }

    public void addNewKombat() {
        Bundle arguments = getIntent().getExtras();
        Kombat kombat;
        if (arguments != null) {
            kombat = (Kombat) arguments.getSerializable(Kombat.class.getSimpleName());
            if (kombat == null) return;
        } else {
            return;
        }
        mkViewModel.addNewKombat(kombat);
        getIntent().removeExtra(Kombat.class.getSimpleName());
    }

    private void Initialize() {
    }
    private File getExternalPath() {
        return(new File(Environment.getExternalStorageDirectory(), "characters.json"));
    }
    public void openText(View view){
        if(downloadCharacters)return;
        if(!permissionGranted) {
            checkPermissions();
            if (!permissionGranted)
                return;
        }

        FileInputStream fin = null;
        File file = getExternalPath();
        if(!file.exists()) return;
        try {
            fin =  new FileInputStream(file);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            mkViewModel.setCharactersList(text);
            downloadCharacters = true;
        }
        catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public boolean isExternalStorageWriteable(){
        String state = Environment.getExternalStorageState();
        return  Environment.MEDIA_MOUNTED.equals(state);
    }
    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        return  (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }
    private boolean checkPermissions() {
        if (!isExternalStorageReadable() || !isExternalStorageWriteable()) {
            Toast.makeText(this, "Внешнее хранилище не доступно", Toast.LENGTH_LONG).show();
            return false;
        }
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_WRITE);
            return false;
        } else{
            permissionGranted = true;
        }
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case REQUEST_PERMISSION_WRITE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    permissionGranted = true;
                    Toast.makeText(this, "Разрешения получены", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this, "Необходимо дать разрешения", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
