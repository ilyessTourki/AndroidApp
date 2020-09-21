package ws.wolfsoft.signup;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("response")
    private String Response;

    @SerializedName("mts")
    private String Mts;

    @SerializedName("mte")
    private String Mte;

    @SerializedName("tts")
    private String Tts;

    @SerializedName("tte")
    private String Tte;

    @SerializedName("wts")
    private String Wts;

    @SerializedName("wte")
    private String Wte;

    @SerializedName("fts")
    private String Fts;

    @SerializedName("fte")
    private String Fte;

    @SerializedName("thts")
    private String Thts;

    @SerializedName("thte")
    private String Thte;

    @SerializedName("sts")
    private String Sts;

    @SerializedName("ste")
    private String Ste;

    public String getMte() {
        return Mte;
    }

    public String getTts() {
        return Tts;
    }

    public String getTte() {
        return Tte;
    }

    public String getWts() {
        return Wts;
    }

    public String getWte() {
        return Wte;
    }

    public String getFts() {
        return Fts;
    }

    public String getFte() {
        return Fte;
    }

    public String getThts() {
        return Thts;
    }

    public String getThte() {
        return Thte;
    }

    public String getSts() {
        return Sts;
    }

    public String getSte() {
        return Ste;
    }



    public String getResponse() {
        return Response;
    }

    public String getMts() {
        return Mts;
    }
}
