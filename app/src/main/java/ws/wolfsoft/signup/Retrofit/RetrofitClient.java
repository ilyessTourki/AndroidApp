package ws.wolfsoft.signup.Retrofit;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {


    private static final String BASE_URL = "http://10.0.2.2:8080/minipro/public/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient() {



        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public INodeJS getApi(){
        return retrofit.create(INodeJS.class);
    }

//    private static Retrofit instance;
//
//    public static Retrofit getInstance() {
//
//        if(instance==null)
//            instance = new Retrofit.Builder()
//                    .baseUrl("http://10.0.2.2:3000/")
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build();
//        return instance;
//    }

//    public Api getApi(){
//        return instance.create(Api.class);
//    }
}
