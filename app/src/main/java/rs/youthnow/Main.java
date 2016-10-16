package rs.youthnow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ReadRss readRss = new ReadRss(this, recyclerView);
        readRss.execute();

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
            Intent settingIntent = new Intent(Main.this, Podesavanja.class);
            Main.this.startActivity(settingIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_studenti) {
            Intent studentiIntent = new Intent(Main.this, Studenti.class);
            Main.this.startActivity(studentiIntent);
        } else if (id == R.id.nav_dogadjaji) {
            //Intent dogadjajiIntent = new Intent(Main.this, Dogadjaji.class);
            //Main.this.startActivity(dogadjajiIntent);
        } else if (id == R.id.nav_it) {
            //Intent itIntent = new Intent(Main.this, IT.class);
            //Main.this.startActivity(itIntent);
        } else if (id == R.id.nav_nauka) {
            //Intent naukaIntent = new Intent(Main.this, Nauka.class);
            //Main.this.startActivity(naukaIntent);
        } else if (id == R.id.nav_lifestyle) {
            //Intent lifestyleIntent = new Intent(Main.this, Lifestyle.class);
            //Main.this.startActivity(lifestyleIntent);
        } else if (id == R.id.nav_online_psiholog) {
            //Intent onlinePshihologIntent = new Intent(Main.this, OnlinePsiholog.class);
            //Main.this.startActivity(onlinePsihologIntent);
        } else if (id == R.id.nav_kulturiska) {
            //Intent kulturiskaIntent = new Intent(Main.this, Kulturiska.class);
            //Main.this.startActivity(kulturiskaIntent);
        } else if (id == R.id.nav_zabava) {
            //Intent zabavaIntent = new Intent(Main.this, Zabava.class);
            //Main.this.startActivity(zabavaIntent);
        } else if (id == R.id.nav_intervjui) {
            //Intent intervjuiIntent = new Intent(Main.this, Intervjui.class);
            //Main.this.startActivity(intervjuiIntent);
        } else if (id == R.id.nav_kontakt) {
            //Intent kontaktIntent = new Intent(Main.this, Kontakt.class);
            //Main.this.startActivity(kontaktIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
