package utils;

public class PetData extends RequestData {

    private String status;

    public PetData(String id, Object body, String status) {
        super(id, body);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
