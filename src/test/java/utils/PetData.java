package utils;

public class PetData extends RequestData {

    private final String status;

    public PetData(String id, Object body, String status) {
        super(id, body);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static PetDataBuilder builder() {
        return new PetDataBuilder();
    }

    public static class PetDataBuilder {
        private String id;
        private Object body;
        private String status;

        public PetDataBuilder petId(String id) {
            this.id = id;
            return this;
        }

        public PetDataBuilder petId(long id) {
            return petId(String.valueOf(id));
        }

        public PetDataBuilder body(Object body) {
            this.body = body;
            return this;
        }

        public PetDataBuilder status(String status) {
            this.status = status;
            return this;
        }

        public PetData build() {
            return new PetData(id, body, status);
        }
    }
}
