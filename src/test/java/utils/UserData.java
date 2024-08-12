package utils;

public class UserData extends RequestData {

    private final String password;

    public UserData(String username, Object body, String password) {
        super(username, body);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
