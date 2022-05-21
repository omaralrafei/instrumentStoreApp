package org.alrafei.instrumentsstore;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.BundleCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_list_black_24);
        actionBar.setTitle("Instruments Yard");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, new HomePageFragment());
        ft.addToBackStack("home");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.toString().equalsIgnoreCase("guitars")){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    InstrumentsFragment instrumentsFragment = InstrumentsFragment.newInstance("guitar", "name");
                    ft.replace(R.id.fragment_container, instrumentsFragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack("");
                    ft.commit();
                }
                else if(item.toString().equalsIgnoreCase("bass guitars")){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    InstrumentsFragment instrumentsFragment = InstrumentsFragment.newInstance("bass", "name");
                    ft.replace(R.id.fragment_container, instrumentsFragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack("");
                    ft.commit();
                }
                else if (item.toString().equalsIgnoreCase("all")){
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    InstrumentsFragment instrumentsFragment = InstrumentsFragment.newInstance("all", "name");
                    ft.replace(R.id.fragment_container, instrumentsFragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack("");
                    ft.commit();
                }

                drawerLayout.closeDrawers();
                Toast.makeText(MainActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 1) {
            moveTaskToBack(false);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_cart) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new CartFragment());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack("");
            ft.commit();
            Toast.makeText(this, "Went to cart", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.action_home) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, new HomePageFragment());
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }
        else
            drawerLayout.openDrawer(GravityCompat.START);

        return super.onOptionsItemSelected(item);
    }
}