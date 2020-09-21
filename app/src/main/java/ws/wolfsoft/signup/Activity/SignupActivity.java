package ws.wolfsoft.signup.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;




import io.reactivex.disposables.CompositeDisposable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.INodeJS;
import ws.wolfsoft.signup.Retrofit.RetrofitClient;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.models.DefaultResponse;
import ws.wolfsoft.signup.models.LoginResponse;

public class SignupActivity extends AppCompatActivity {

 TextView terms;
    TextView create;
    TextView conti;
    EditText name;
    EditText email;
    EditText pass;

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
    protected void onStart() {
        super.onStart();
        if(ShardPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"font/Lato-Light.ttf");


        terms = (TextView)findViewById(R.id.terms);
        create = (TextView)findViewById(R.id.create);
        conti = (TextView)findViewById(R.id.conti);
        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.age);
        pass = (EditText)findViewById(R.id.pass);
        pass.setTypeface(custom_font);
        terms.setTypeface(custom_font);
        create.setTypeface(custom_font);
        conti.setTypeface(custom_font);
        name.setTypeface(custom_font);
        email.setTypeface(custom_font);




        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignupActivity.this,SigninActivity.class);
                startActivity(it);

            }
        });

        conti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignUp();
            }
        });


    }

    private void userSignUp(){
        String eemail = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String nname = name.getText().toString().trim();


        if (eemail.isEmpty()) {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(eemail).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            pass.setError("Password required");
            pass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            pass.setError("Password should be atleast 6 character long");
            pass.requestFocus();
            return;
        }

        if (nname.isEmpty()) {
            name.setError("Name required");
            name.requestFocus();
            return;
        }
        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(nname, eemail, password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if(response.code() == 201){



                    DefaultResponse dr = response.body();
                    Toast.makeText(SignupActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignupActivity.this, SigninActivity.class));

                }else if(response.code() == 422){
                    Toast.makeText(SignupActivity.this, "User already exist", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //Toast.makeText(SignupActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
