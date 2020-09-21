package ws.wolfsoft.signup.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ws.wolfsoft.signup.Fragments.Friday;
import ws.wolfsoft.signup.Fragments.Monday;
import ws.wolfsoft.signup.Fragments.Saturday;
import ws.wolfsoft.signup.Fragments.Thursday;
import ws.wolfsoft.signup.Fragments.Tuesday;
import ws.wolfsoft.signup.Fragments.Wednesday;
import ws.wolfsoft.signup.R;

public class ConseilTime extends AppCompatActivity {
    Button mon,tue,wed,thu,fri,sat;
    Fragment fragment;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conseil_time);
        mon= findViewById(R.id.monday);
        tue= findViewById(R.id.tuesday);
        wed= findViewById(R.id.wednesday);
        thu= findViewById(R.id.Thursday);
        fri= findViewById(R.id.Friday);
        sat= findViewById(R.id.saturday);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        final int vv = extras.getInt("1");
        final int v1 = extras.getInt("2");
        final int v2 = extras.getInt("a1");
        final int v3 = extras.getInt("a2");
        final int v4 = extras.getInt("aa1");
        final  int v5 = extras.getInt("aa2");

        final int va= extras.getInt("3");
        final int va1 = extras.getInt("4");
        final int va2 = extras.getInt("b1");
        final int va3 = extras.getInt("b2");
        final int va4 = extras.getInt("bb1");
        final int va5 = extras.getInt("bb2");

        final int val = extras.getInt("5");
        final int val1 = extras.getInt("6");
        final int val2 = extras.getInt("c1");
        final int val3 = extras.getInt("c2");
        final  int val4 = extras.getInt("cc1");
        final int val5= extras.getInt("cc2");

        final int vale = extras.getInt("7");
        final int vale1 = extras.getInt("8");
        final int vale2 = extras.getInt("d1");
        final int vale3 = extras.getInt("d2");
        final int vale4 = extras.getInt("d11");
        final int vale5 = extras.getInt("d22");

        final int valeu = extras.getInt("9");
        final int valeu1 = extras.getInt("10");
        final int valeu2 = extras.getInt("e1");
        final int valeu3 = extras.getInt("e2");
        final int valeu4 = extras.getInt("e11");
        final int valeu5 = extras.getInt("e22");

        final int valeur = extras.getInt("11");
        final int valeur1 = extras.getInt("12");
        final int valeur2 = extras.getInt("f1");
        final  int valeur3 = extras.getInt("f2");
        final int valeur4 = extras.getInt("ff1");
        final  int valeur5 = extras.getInt("ff2");


        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("1", vv );
                bundle.putInt("2", v1 );
                bundle.putInt("3", v2 );
                bundle.putInt("4", v3 );
                bundle.putInt("5", v4 );
                bundle.putInt("6", v5 );
                fragment = new Monday();
                fragment.setArguments(bundle);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, fragment).commit();

            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("11", va );
                bundle.putInt("22", va1 );
                bundle.putInt("33", va2 );
                bundle.putInt("44", va3 );
                bundle.putInt("55", va4 );
                bundle.putInt("66", va5 );
                fragment = new Tuesday();
                fragment.setArguments(bundle);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, fragment).commit();

            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("111", val );
                bundle.putInt("222", val1 );
                bundle.putInt("333", val2 );
                bundle.putInt("444", val3 );
                bundle.putInt("555", val4 );
                bundle.putInt("666", val5 );
                fragment = new Wednesday();
                fragment.setArguments(bundle);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, fragment).commit();


            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("7", vale );
                bundle.putInt("8", vale1 );
                bundle.putInt("9", vale2 );
                bundle.putInt("10", vale3 );
                bundle.putInt("11", vale4 );
                bundle.putInt("12", vale5 );
                fragment = new Thursday();
                fragment.setArguments(bundle);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, fragment).commit();


            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("77", valeu );
                bundle.putInt("88", valeu1 );
                bundle.putInt("99", valeu2 );
                bundle.putInt("1010", valeu3 );
                bundle.putInt("1111", valeu4 );
                bundle.putInt("1212", valeu5 );
                fragment = new Friday();
                fragment.setArguments(bundle);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, fragment).commit();


            }
        });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("777", valeur );
                bundle.putInt("888", valeur1 );
                bundle.putInt("999", valeur2 );
                bundle.putInt("101010", valeur3 );
                bundle.putInt("111111", valeur4 );
                bundle.putInt("121212", valeur5 );
                fragment = new Saturday();
                fragment.setArguments(bundle);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment, fragment).commit();


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.retour, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.RetourMenu:
                startActivity(new Intent(ConseilTime.this, HomeActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
