package aoss.assignment.restservice.models.users;

/* Created by Kuyan Kirill
   Email: progingisfun@gmail.com
   Date: 14.04.2020 */

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class AuthLog {

    private int id;
    private String loginTime;
    private String logoutTime;
    private int userId;

    private static SimpleDateFormat timeFormat;

    static {
        timeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        timeFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public AuthLog() {
    }

    public AuthLog(int id, String loginTime, String logoutTime, int userId) {
        this.id = id;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static AuthLog authLogWithUserAndCurrentTime(int id){
        return new AuthLog(0,timeFormat.format(new Date()),"",id);
    }

    public static String currentTime(){
        return timeFormat.format(new Date());
    }
}
