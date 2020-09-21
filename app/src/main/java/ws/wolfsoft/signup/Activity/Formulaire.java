package ws.wolfsoft.signup.Activity;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.INodeJS;
import ws.wolfsoft.signup.Retrofit.RetrofitClient;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.models.DefaultResponse;
import ws.wolfsoft.signup.models.User;

public class Formulaire extends AppCompatActivity {
    Spinner spinner,spin;
    private Button next;
    EditText aage;

    INodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);

        aage = findViewById(R.id.age);
        spinner = findViewById(R.id.spinner_view);
        spin= findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Student");
        categories.add("Work");
        categories.add("none");
        List<String> Etatsocial = new ArrayList<String>();
        Etatsocial.add("Single");
        Etatsocial.add("in a relationship");
       // Etatsocial.add("marriage");



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Etatsocial);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapt);
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AddFormulair();

            }
        });


    }

    private void AddFormulair(){

        String age = aage.getText().toString().trim();
        String activite = spinner.getAdapter().toString().trim();
        String situation = spin.getAdapter().toString().trim();


        if (age.isEmpty()) {
            aage.setError("age is required");
            aage.requestFocus();
            return;
        }

        User user = ShardPrefManager.getInstance(this).getUser();

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createFormulair(user.getId(),age, activite, situation);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if(response.code() == 201){

                    DefaultResponse dr = response.body();
                    Toast.makeText(Formulaire.this, dr.getMsg(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Formulaire.this, Subject.class));

                }else if(response.code() == 422){
                    startActivity(new Intent(Formulaire.this, Subject.class));
                    Toast.makeText(Formulaire.this, "Formulaire already exist", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //Toast.makeText(SignupActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}
