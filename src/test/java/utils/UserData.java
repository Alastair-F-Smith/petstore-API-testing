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

    public static UserDataBuilder builder() {
        return new UserDataBuilder();
    }

    public static class UserDataBuilder {
        private String username;
        private Object body;
        private String password;

        public UserDataBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserDataBuilder body(Object body) {
            this.body = body;
            return this;
        }

        public UserDataBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserData build() {
            return new UserData(username, body, password);
        }
    }
}
