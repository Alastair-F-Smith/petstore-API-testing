package utils.requests;

public enum HttpMethods {
    GET, POST, DELETE;

    public static HttpMethods from(String httpMethod) {
        return switch(httpMethod.toUpperCase()) {
            case "GET" -> GET;
            case "POST" -> POST;
            case "DELETE" -> DELETE;
            default -> throw new IllegalArgumentException("Invalid HTTP method");
        };
    }
}
