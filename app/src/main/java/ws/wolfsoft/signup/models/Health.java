package ws.wolfsoft.signup.models;

public class Health {

    private int id, userid;
    private String height,weight,imc,are,must;

    public Health(int id, int userid, String height, String weight, String imc, String are, String must) {
        this.id = id;
        this.userid = userid;
        this.height = height;
        this.weight = weight;
        this.imc = imc;
        this.are = are;
        this.must = must;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getImc() {
        return imc;
    }

    public String getAre() {
        return are;
    }

    public String getMust() {
        return must;
    }
}
