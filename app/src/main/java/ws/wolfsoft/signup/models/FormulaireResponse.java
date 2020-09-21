package ws.wolfsoft.signup.models;

public class FormulaireResponse {

    private boolean error;
    private String message;
    private formulaire formulaire;

    public FormulaireResponse(boolean error, String message, ws.wolfsoft.signup.models.formulaire formulaire) {
        this.error = error;
        this.message = message;
        this.formulaire = formulaire;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public formulaire getFormulaire() {
        return formulaire;
    }
}
