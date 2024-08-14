package utils.requests;

public enum HttpMethods {
    GET, POST, PUT, DELETE;

    public static HttpMethods from(String httpMethod) {
        return switch(httpMethod.toUpperCase()) {
            case "GET" -> GET;
            case "POST" -> POST;
            case "DELETE" -> DELETE;
            case "PUT" -> PUT;
            default -> throw new IllegalArgumentException("Invalid HTTP method");
        };
    }
}
