package entity;

import java.io.Serializable;

public class admin  extends entity {


    private String password;
    private String login;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "admin{" +
                "password='" + password + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
