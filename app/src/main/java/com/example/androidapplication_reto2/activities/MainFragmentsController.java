package com.example.androidapplication_reto2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;


import com.example.androidapplication_reto2.R;
import com.google.android.material.navigation.NavigationView;

public class MainFragmentsController extends AppCompatActivity{

    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DrawerLayout drawerLayout=findViewById(R.id.drawer);
        NavigationView navigationView=findViewById(R.id.nav_view);

        appBarConfiguration= new AppBarConfiguration.Builder(R.id.nav_home,
                R.id.nav_user_data,R.id.nav_join_group,
                R.id.nav_user_groups,R.id.nav_search_documents)
                .setDrawerLayout(drawerLayout).build();

        navController = Navigation
                .findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);



    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
