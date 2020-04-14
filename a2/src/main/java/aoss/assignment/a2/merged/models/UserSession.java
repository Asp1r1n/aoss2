package aoss.assignment.a2.merged.models;

public class UserSession {
    private String token;

    public UserSession(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
