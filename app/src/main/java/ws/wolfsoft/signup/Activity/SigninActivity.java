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
import ws.wolfsoft.signup.models.LoginResponse;
import ws.wolfsoft.signup.models.User;

public class SigninActivity extends AppCompatActivity {

    EditText email;
    EditText pass;
    TextView wolf;
    TextView signin;
    TextView Create;


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

        if (ShardPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"font/Lato-Light.ttf");

        email = (EditText)findViewById(R.id.age);
        pass = (EditText)findViewById(R.id.pass);
        wolf = (TextView) findViewById(R.id.wolf);
         signin = (TextView) findViewById(R.id.signin);
        Create = (TextView)findViewById(R.id.Create);
        Create.setTypeface(custom_font);
        email.setTypeface(custom_font);
        pass.setTypeface(custom_font);

        signin.setTypeface(custom_font);


        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(it);

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


    }

    private void userLogin() {

        String eemail = email.getText().toString().trim();
        String password = pass.getText().toString().trim();

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

        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(eemail, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (!loginResponse.isError()) {

                    ShardPrefManager.getInstance(SigninActivity.this)
                            .saveUser(loginResponse.getUser());

                    Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                    Toast.makeText(SigninActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(SigninActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

}
