package ws.wolfsoft.signup.Retrofit;



import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ws.wolfsoft.signup.models.DefaultResponse;
import ws.wolfsoft.signup.models.FormulaireResponse;
import ws.wolfsoft.signup.models.HealthResponse;
import ws.wolfsoft.signup.models.LoginResponse;
import ws.wolfsoft.signup.models.MoneyResponse;

public interface INodeJS {

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("name") String name,
            @Field("email") String email

    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );

    @DELETE("deleteuser/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);


    @FormUrlEncoded
    @POST("createformulaire")
    Call<DefaultResponse> createFormulair(
            @Field("userid") int userid,
            @Field("age") String age,
            @Field("activite") String activite,
            @Field("situation") String situation
    );

    @FormUrlEncoded
    @POST("formulairelogin")
    Call<FormulaireResponse> FormulaireLogin(
            @Field("userid") int userid
    );

    @FormUrlEncoded
    @POST("createmoney")
    Call<DefaultResponse> createMoney(
            @Field("userid") int userid,
            @Field("saler") String saler,
            @Field("rent") String rent,
            @Field("habit") String habit
    );

    @FormUrlEncoded
    @POST("moneylogin")
    Call<MoneyResponse> MoneyLogin(
            @Field("userid") int userid
    );

    @FormUrlEncoded
    @PUT("updatemoney/{id}")
    Call<MoneyResponse> updateMoney(
            @Path("id") int id,
            @Field("saler") String saler,
            @Field("rent") String rent,
            @Field("habit") String habit,
            @Field("userid") int userid

    );

    @DELETE("deletemoney/{id}")
    Call<DefaultResponse> deleteMoney(@Path("id") int id);

    @FormUrlEncoded
    @POST("createhealth")
    Call<DefaultResponse> createHealth(
            @Field("userid") int userid,
            @Field("height") String height,
            @Field("weight") String weight,
            @Field("imc") String imc,
            @Field("are") String are,
            @Field("must") String must
    );

    @FormUrlEncoded
    @POST("healthlogin")
    Call<HealthResponse> HealthLogin(
            @Field("userid") int userid
    );

    @FormUrlEncoded
    @PUT("updatehealth/{id}")
    Call<HealthResponse> updateHealth(
            @Path("id") int id,
            @Field("height") String height,
            @Field("weight") String weight,
            @Field("userid") int userid

    );

    @DELETE("deletehealth/{id}")
    Call<DefaultResponse> deleteHealth(@Path("id") int id);





//    @POST("register")
//    @FormUrlEncoded
//    Observable<String> registerUser(@Field("name") String email,
//                                    @Field("email") String name,
//                                    @Field("password") String password);
//
//
//    @POST("login")
//    @FormUrlEncoded
//    Observable<String> loginUser(@Field("email") String email,
//                                 @Field("password") String password);
//
//    @POST("registerformulair")
//    @FormUrlEncoded
//    Observable<String> registerformulair(@Field("user_id") int user_id,
//                                         @Field("age") String age,
//                                         @Field("activite") String activite,
//                                         @Field("situation") String situation);

}
