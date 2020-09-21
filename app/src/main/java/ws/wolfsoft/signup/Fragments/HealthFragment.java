package ws.wolfsoft.signup.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ws.wolfsoft.signup.R;
import ws.wolfsoft.signup.Retrofit.RetrofitClient;
import ws.wolfsoft.signup.Storage.ShardHealthManager;
import ws.wolfsoft.signup.Storage.ShardPrefManager;
import ws.wolfsoft.signup.models.DefaultResponse;
import ws.wolfsoft.signup.models.Health;
import ws.wolfsoft.signup.models.HealthResponse;
import ws.wolfsoft.signup.models.User;

public class HealthFragment extends Fragment {

    EditText Height,Weight;
    TextView updat, delete;
    public TextView Message,imc,are,must,at;
    String we,he,G,P;
    int h,w,gg,pp;
    double s,c,g,p;
    LinearLayout health;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_aff_health, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Message = view.findViewById(R.id.create);
        imc=view.findViewById(R.id.imc);
        are=view.findViewById(R.id.are);
        must=view.findViewById(R.id.must);
        at=view.findViewById(R.id.at);
        Weight = view.findViewById(R.id.height);
        Height = view.findViewById(R.id.weight);
        health=view.findViewById(R.id.health);

        updat= view.findViewById(R.id.updatInfo2);
        delete= view.findViewById(R.id.delete2);

        HealthLogin();

        updat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateHealth();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteHealth();
            }
        });


    }

    private void deleteHealth() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are you sure?");
        builder.setMessage("This action is irreversible...");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Health health = ShardHealthManager.getInstance(getActivity()).getHealth();
                Call<DefaultResponse> call = RetrofitClient.getInstance().getApi().deleteHealth(health.getId());

                call.enqueue(new Callback<DefaultResponse>() {
                    @Override
                    public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                        if (!response.body().isErr()) {
                            ShardHealthManager.getInstance(getActivity()).clear();
                            ShardHealthManager.getInstance(getActivity()).clear();
                            HealthLogin();
                        }

                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<DefaultResponse> call, Throwable t) {

                    }
                });

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog ad = builder.create();
        ad.show();

    }

    private void updateHealth() {

        String hheight = Height.getText().toString().trim();
        String wweight = Weight.getText().toString().trim();


        if (hheight.isEmpty()) {
            Height.setError("height is required");
            Height.requestFocus();
            return;
        }
        if (wweight.isEmpty()) {
            Weight.setError("weight is required");
            Weight.requestFocus();
            return;
        }

        User user = ShardPrefManager.getInstance(getActivity()).getUser();
        Health health = ShardHealthManager.getInstance(getActivity()).getHealth();

        Call<HealthResponse> call = RetrofitClient.getInstance().getApi()
                .updateHealth(health.getId(),hheight,wweight,user.getId());

        call.enqueue(new Callback<HealthResponse>() {
            @Override
            public void onResponse(Call<HealthResponse> call, Response<HealthResponse> response) {
                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                if (!response.body().isError()) {
                    ShardHealthManager.getInstance(getActivity()).saveHealth(response.body().getHealth());
                }
            }

            @Override
            public void onFailure(Call<HealthResponse> call, Throwable t) {

            }
        });
    }

    private void HealthLogin(){

        User user = ShardPrefManager.getInstance(getActivity()).getUser();

        Call<HealthResponse> call = RetrofitClient
                .getInstance().getApi().HealthLogin(user.getId());

        call.enqueue(new Callback<HealthResponse>() {
            @Override
            public void onResponse(Call<HealthResponse> call, Response<HealthResponse> response) {
                HealthResponse healthResponse = response.body();

                if (!healthResponse.isError()) {

                    ShardHealthManager.getInstance(getActivity())
                            .saveHealth(healthResponse.getHealth());

                    Message.setVisibility(View.INVISIBLE);
                    health.setVisibility(View.VISIBLE);
                    Weight.setText(ShardHealthManager.getInstance(getActivity()).getHealth().getWeight());
                    Height.setText(ShardHealthManager.getInstance(getActivity()).getHealth().getHeight());

                    we = Weight.getText().toString();
                    he = Height.getText().toString();
                    if(!he.equals("")){h = Integer.parseInt(he);}
                    if(!we.equals("")){w = Integer.parseInt(we);}
                    s= (w)/(h*h/10000);
                    c = 25*h*h/10000;
                    g=c-w;
                    p=w-c;
                    gg= (int) g;
                    pp= (int) p;
                    imc.setText(String.valueOf(s));
                    G= String.valueOf(gg);
                    P= String.valueOf(pp);

                    if(s<18.5){
                        are.setText("Insufficient weight (thinness)");
                        must.setText("gain weight by ");
                        at.setText(G+"Kg");
                    }
                    else if(s>=18.5 && s<25 ){
                        are.setText("Normal body");
                        must.setText("Stay like you are That is good");
                    }
                    else if(s>=25 && s<30 ){
                        are.setText("overweight");
                        must.setText("slim by");
                        at.setText(P+"Kg");
                    }
                    else if(s>=30 && s<35 ){
                        are.setText("Moderate obesity");
                        must.setText("slim by");
                        at.setText(P+"Kg");
                    }
                    else if(s>=35 && s<40 ){
                        are.setText("Severe obesity");
                        must.setText("slim by ");
                        at.setText(P+"Kg");
                    }
                    else{
                        are.setText("Morbid or massive obesity");
                        must.setText("slim by");
                        at.setText(P+"Kg");
                    }

                } else {
                    health.setVisibility(View.INVISIBLE);
                    Message.setText("Health formulaire not exist");
                }
            }

            @Override
            public void onFailure(Call<HealthResponse> call, Throwable t) {

            }
        });

    }
}
