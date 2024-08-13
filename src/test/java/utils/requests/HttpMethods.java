package utils.requests;

public enum HttpMethods {
    GET, POST;

    public static HttpMethods from(String httpMethod) {
        return switch(httpMethod.toUpperCase()) {
            case "GET" -> GET;
            case "POST" -> POST;
            default -> throw new IllegalArgumentException("Invalid HTTP method");
        };
    }
}
