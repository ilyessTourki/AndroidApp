package ws.wolfsoft.signup.models;

public class Money {

    private int id, userid;
    private String saler, rent,habit;

    public Money(int id, int userid, String saler, String rent, String habit) {
        this.id = id;
        this.userid = userid;
        this.saler = saler;
        this.rent = rent;
        this.habit = habit;
    }

    public int getId() {
        return id;
    }

    public int getUserid() {
        return userid;
    }

    public String getSaler() {
        return saler;
    }

    public String getRent() {
        return rent;
    }

    public String getHabit() {
        return habit;
    }
}
