package ws.wolfsoft.signup.models;

public class HealthResponse {

    private boolean error;
    private String message;
    private Health health;

    public HealthResponse(boolean error, String message, Health health) {
        this.error = error;
        this.message = message;
        this.health = health;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Health getHealth() {
        return health;
    }
}
