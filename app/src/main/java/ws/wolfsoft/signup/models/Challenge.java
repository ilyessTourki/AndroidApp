package ws.wolfsoft.signup.models;

import com.google.gson.annotations.SerializedName;

public class Challenge {



    @SerializedName("response")
    private String Response;
    @SerializedName("id")
    private int ID;
    @SerializedName("titre")
    private String Titre;

    @SerializedName("description")
    private String Description;

    @SerializedName("type")
    private int Type;

    @SerializedName("datefin")
    private String Datefin;


    public Challenge(String titre, String datefin) {
        Titre = titre;
        Datefin = datefin;
    }

    public Challenge() {
    }

    public Challenge(String response, int ID, String titre, String description, int type, String datefin) {
        Response = response;
        this.ID = ID;
        Titre = titre;
        Description = description;
        Type = type;
        Datefin = datefin;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getDatefin() {
        return Datefin;
    }

    public void setDatefin(String datefin) {
        Datefin = datefin;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "Response='" + Response + '\'' +
                ", ID=" + ID +
                ", Titre='" + Titre + '\'' +
                ", Description='" + Description + '\'' +
                ", Type=" + Type +
                ", Datefin='" + Datefin + '\'' +
                '}';
    }
}
