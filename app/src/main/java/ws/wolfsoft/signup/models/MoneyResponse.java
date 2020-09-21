package ws.wolfsoft.signup.models;

public class MoneyResponse {

    private boolean error;
    private String message;
    private Money money;

    public MoneyResponse(boolean error, String message, Money money) {
        this.error = error;
        this.message = message;
        this.money = money;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Money getMoney() {
        return money;
    }
}
