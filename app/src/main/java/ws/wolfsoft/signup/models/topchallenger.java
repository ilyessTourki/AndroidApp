package ws.wolfsoft.signup.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class topchallenger {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("response")
    @Expose
    private String response;

    private  int classement;

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}