package ws.wolfsoft.signup.models;

public class formulaire {

    private int id, userid;
    private String age,activite,situation;

    public formulaire(int id, int userid, String age, String activite, String situation) {
        this.id = id;
        this.userid = userid;
        this.age = age;
        this.activite = activite;
        this.situation = situation;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getAge() {
        return age;
    }

    public String getActivite() {
        return activite;
    }

    public String getSituation() {
        return situation;
    }
}
