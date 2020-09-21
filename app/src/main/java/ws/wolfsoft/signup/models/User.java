package ws.wolfsoft.signup.models;

import java.util.Date;

public class User {

    private int id;
    private String name,email;
    private int challid,point;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(int id, String name, String email, int challid, int point) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.challid = challid;
        this.point = point;
    }

    public User(int id, String name, String email, int point) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.point = point;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getChallid() {
        return challid;
    }

    public int getPoint() {
        return point;
    }
}
