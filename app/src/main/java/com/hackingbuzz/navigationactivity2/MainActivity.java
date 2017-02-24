package com.hackingbuzz.navigationactivity2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Gallery;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // set DrawerListener is depricated ...use addDrawerListener
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Toast.makeText(MainActivity.this, "Importing Image from Camera ",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {
           // see the thing is why we using fragments...1. when you cant move to another activity means this activity contains navigation drawer if you go to other activity you cant attract with navivation mewnu n its gone then
            //2. you can replace the whole screen (that use view ) with another fragment ...(but you can replace an activty you have to go to other activity ) you can replace as many fragments(sceen) in one activity



            GalleryFragment fragment = new GalleryFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main,fragment, fragment.getTag()).commit();

            // you can do all this in one line see  below !!

          //  GalleryFragment galleryFragment = new GalleryFragment();
            // make sure to get supportFragmentManger..
           // getSupportFragmentManager().beginTransaction().replace(R.id.content_main,galleryFragment , galleryFragment.getTag()).commit();  // we have to replace xml file (i mean screen so we goota tell him viewroot inside our frngment views are


        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(MainActivity.this, "Showing a slideshow ",Toast.LENGTH_SHORT).show();

            // you dont know which fragment you open first you should always replace a activity (xml viewroot element ) but you can put also any fragment layout that you wanna replace...

            SlideShowFragment slideShowFragment = new SlideShowFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,slideShowFragment,"slide").commit();

        } else if (id == R.id.nav_manage) {
            Toast.makeText(MainActivity.this, "Camera is Opening ",Toast.LENGTH_SHORT).show();

        }

         else if (id == R.id.nav_send) {
            Toast.makeText(MainActivity.this, "Image is Sending ",Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
