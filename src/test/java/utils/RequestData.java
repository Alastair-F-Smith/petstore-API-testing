package utils;

public class RequestData {

    private final String id;
    private final Object body;

    public RequestData(String id, Object body) {
        this.id = id;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public Object getBody() {
        return body;
    }


}
