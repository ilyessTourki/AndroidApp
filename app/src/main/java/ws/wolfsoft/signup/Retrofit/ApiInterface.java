package ws.wolfsoft.signup.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ws.wolfsoft.signup.Challenge;
import ws.wolfsoft.signup.User;
import ws.wolfsoft.signup.models.topchallenger;

public interface ApiInterface {

    @GET("timeinsert.php")
    Call<User> performRegister(@Query("mts") String Mts, @Query("mte") String Mte, @Query("tts") String Tts, @Query("tte") String Tte, @Query("wte") String Wte, @Query("wts") String Wts, @Query("fts") String Fts, @Query("fte") String Fte, @Query("thts") String Thts, @Query("thte") String Thte, @Query("sts") String Sts, @Query("ste") String Ste, @Query("sport") String Sport, @Query("mss") String Mss, @Query("mse") String Mse, @Query("tss") String Tss, @Query("tse") String Tse, @Query("wss") String Wss, @Query("wse") String Wse, @Query("tuss") String Tuss, @Query("tuse") String Tuse, @Query("fss") String Fss, @Query("fse") String Fse, @Query("sss") String Sss, @Query("sse") String Sse, @Query("suss") String Suss, @Query("suse") String Suse, @Query("asso") String Asso, @Query("dateasso") String Dateasso, @Query("userid") int Iduser);
    @GET("affiche.php")
    Call<User> information(@Query("userid") int Userid);
    @GET("challengeinsert.php")
    Call<Challenge> chellengeinser(@Query("titre") String Titre, @Query("description") String Description, @Query("type") int Type, @Query("datefin") String Datefin);
    @GET("affichechallenge.php")
    Call<Challenge> challenges(@Query("id") int ID, @Query("type") int Type);
    @GET("count.php")
    Call<Challenge> count();
    @GET("updateuser.php")
    Call<topchallenger> updateuser(@Query("userid") int Userid, @Query("challid") int Challid, @Query("point") int Point);
    @GET("topscore.php")
    Call<List<topchallenger>> topscore();
    @GET("username.php")
    Call<User> username(@Query("id") int ID);
    @GET("affichechallengeuser.php")
    Call<Challenge> challengeuser(@Query("id") int ID);
    @GET("cahlliduser.php")
    Call<List<topchallenger>> challiduser(@Query("userid") int Userid);
    @GET("moneytest.php")
    Call<User> moneytest(@Query("id") int ID);
    @GET("healthtest.php")
    Call<User> healthtest(@Query("id") int ID);


}

