package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Adapters.KombatAdapter;
import com.example.myapplication.MKCorePack.JSONHelper;
import com.example.myapplication.MKCorePack.Kombat;
import com.example.myapplication.MKCorePack.Variation;
import com.example.myapplication.MKCorePack.Character;
import com.example.myapplication.ViewModels.HomeFragment;
import com.example.myapplication.ViewModels.MKViewModel;
//import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MKViewModel mkViewModel;

    List<Kombat> kombatArrayList = new ArrayList();
    public static final String ACTION ="com.example.myapplication.activitytwo";
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.bottom_navigation_item_logs:
//                    loadFragment(HomeFragment.newInstance());
//                    return true;
//                case R.id.bottom_navigation_item_profile:
//                    loadFragment(HomeFragment.newInstance());
//                    return true;
//                case R.id.bottom_navigation_item_progress:
//                    loadFragment(HomeFragment.newInstance());
//                    return true;
//            }
//            return false;
//        }
//    };
//    private void loadFragment(Fragment fragment) {
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fl_content, fragment);
//        ft.commit();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mkViewModel = new MKViewModel();
        Initialize();
//        ListView kombatListView = findViewById(R.id.KombatListViewId);
//        KombatAdapter kombatAdapter = new KombatAdapter(this, R.layout.kombat_list_item, kombatArrayList);
//        kombatListView.setAdapter(kombatAdapter);
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
        if(item.getTitle().equals("kombats")) {
            Intent intent = new Intent(this, ActivityTwo.class);
            intent.putExtra(ArrayList.class.getSimpleName(), mkViewModel.GetKombatsList());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void addNewKombat(View view) {
        //mkViewModel.addNewKombat();
    }

    private void Initialize() {
//        Character scorp = new Character("Scorpion", new Variation("Hellfire"), R.drawable.scorpion);
//        Character sub = new Character("Sub Zero", new Variation("Ice"), R.drawable.sub_zero);
//        kombatArrayList.add(new Kombat(scorp, sub, 0));
//        kombatArrayList.add(new Kombat(scorp, sub, 1));
//        kombatArrayList.add(new Kombat(sub, scorp, 0));
        kombatArrayList = JSONHelper.importFromJSON(this);
        mkViewModel.setKombatsList(kombatArrayList);
//        List<Kombat> res = JSONHelper.importFromJSON(this);
//        for (Kombat ks : res){
//            kombatArrayList.add(ks);
//        }
    }
}
