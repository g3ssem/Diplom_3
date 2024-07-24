package api;

public class User {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword () {
        return password;
    }

    private String password;

    public String getUserName() {
        return name;
    }

    public void setUserName(String userName) {
        this.name = name;
    }

    private String name;
    public User (String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User () {

    }

}
