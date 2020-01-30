package com.example.androidapplication_reto2.project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;


import com.example.androidapplication_reto2.R;
import com.example.androidapplication_reto2.project.activities.navigationfragments.PrincipalUserFragment;
import com.example.androidapplication_reto2.project.beans.User;
import com.google.android.material.navigation.NavigationView;

public class MainFragmentsController extends AppCompatActivity{

    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    /**
     * Action when creates the activity that is going to include the app fragments and the navigation darwer for that fragments
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer.create(this, R.raw.hello).start();

        //Navigation drawer elements declaration
        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout=findViewById(R.id.drawer);
        NavigationView navigationView=findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);
        appBarConfiguration= new AppBarConfiguration.Builder(R.id.nav_home,
                R.id.nav_user_data,R.id.nav_join_group,
                R.id.nav_user_groups,R.id.nav_search_documents)
                .setDrawerLayout(drawerLayout).build();
        navController = Navigation
                .findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);



    }

    /**
     * Action when the navigation is being showed
     * @return true if is being showed
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController,appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
