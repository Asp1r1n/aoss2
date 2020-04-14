package aoss.assignment.a2.merged.controllers.auth;

import aoss.assignment.a2.merged.helpers.App;
import aoss.assignment.a2.merged.helpers.Connector;
import aoss.assignment.a2.merged.helpers.JsonParser;
import aoss.assignment.a2.merged.models.Item;
import aoss.assignment.a2.merged.models.Order;
import aoss.assignment.a2.merged.models.OrderItem;
import aoss.assignment.a2.merged.models.UserSession;
import com.mysql.cj.Session;

import java.util.ArrayList;

public class AuthController {
    public String address;
    private String serverAddress;
    private UserSession session;

    public AuthController() {
        this.session = new UserSession(null);
        this.address = App.AUTH;
    }

    public AuthController(UserSession session) {
        this.session = session;
        this.address = App.AUTH;
    }

    public UserSession postToken(String login, String password) {
        System.out.println(serverAddress + address + "/token");
        String loginParams = "login=" + login + "&password=" + password;
        String result = Connector.postLogin(serverAddress + address + "/token", loginParams);

        if (!result.contains("error")) {
            session.setToken(result);
            return session;
        }

        return null;
    }

    public boolean postLogout() {
        Connector.post(serverAddress + address + "/logout", "", session);

        return true;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
}
