package ws.wolfsoft.signup.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.Fragments.HealthFragment;
import ws.wolfsoft.signup.Fragments.HomeFragment;
import ws.wolfsoft.signup.Fragments.MoneyFragment;
import ws.wolfsoft.signup.NewsApi;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Fragments.TimeFragment;
import ws.wolfsoft.signup.Retrofit.RetrofitClient;
import ws.wolfsoft.signup.Storage.ShardFormulaireManager;
import ws.wolfsoft.signup.Storage.ShardHealthManager;
import ws.wolfsoft.signup.Storage.ShardMoneyManager;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.models.FormulaireResponse;
import ws.wolfsoft.signup.models.User;
import ws.wolfsoft.signup.models.formulaire;

public class HomeActivity extends AppCompatActivity {

    Button question1,question2,question3;
    Toolbar toolbar;
    int userid;




    @Override
    protected void onStart() {
        super.onStart();

        if (!ShardPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, SigninActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        User user = ShardPrefManager.getInstance(this).getUser();
        userid = user.getId();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }

        FormulairLogin();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_Time:
                            selectedFragment = new TimeFragment();
                            break;
                        case R.id.nav_Money:
                            selectedFragment = new MoneyFragment();
                            break;
                        case R.id.nav_health:
                            selectedFragment = new HealthFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        formulaire formulaire = ShardFormulaireManager.getInstance(this).getFormulaire();

        switch(item.getItemId()){
            case R.id.logoutMenu:{
                logout();
                break;
            }
            case R.id.profileMenu: {
                if (userid == formulaire.getUserid()){
                    startActivity(new Intent(HomeActivity.this, Subject.class));
                }else{
                    startActivity(new Intent(HomeActivity.this, Formulaire.class));
                }
                break;
            }
            case R.id.accountMenu: {
                startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                break;
            }
            case R.id.listchallenge: {
                int T=4;
                Intent i = new Intent(HomeActivity.this, ListeChallanges.class);
                i.putExtra("9", T);
                startActivity(i);
                break;
            }
            case R.id.topScorres: {
                Intent i = new Intent(HomeActivity.this, Classement.class);
                startActivity(i);
                break;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        ShardPrefManager.getInstance(this).clear();
        ShardFormulaireManager.getInstance(this).clear();
        ShardMoneyManager.getInstance(this).clear();
        ShardHealthManager.getInstance(this).clear();
        Intent intent = new Intent(HomeActivity.this, SigninActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void FormulairLogin(){

        Call<FormulaireResponse> call = RetrofitClient
                .getInstance().getApi().FormulaireLogin(userid);

        call.enqueue(new Callback<FormulaireResponse>() {
            @Override
            public void onResponse(Call<FormulaireResponse> call, Response<FormulaireResponse> response) {
                FormulaireResponse formulaireResponse = response.body();

                if (!formulaireResponse.isError()) {

                    ShardFormulaireManager.getInstance(HomeActivity.this)
                            .saveFormulair(formulaireResponse.getFormulaire());


                } else {

                }
            }

            @Override
            public void onFailure(Call<FormulaireResponse> call, Throwable t) {

            }
        });

    }
}
