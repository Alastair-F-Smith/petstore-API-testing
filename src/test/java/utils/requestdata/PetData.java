package utils.requestdata;

import java.util.HashMap;
import java.util.Map;

public class PetData extends RequestData {

//    private final String status;

    public PetData(String id, Object body, String status) {
        super(body, new HashMap<>(), new HashMap<>(), new HashMap<>());
        getQueryParams().put("status", status);
    }

    public PetData(Object body, Map<String, String> queryParams, Map<String, String> pathParams, Map<String, String> headers) {
        super(body, queryParams, pathParams, headers);
    }

    public String getId() {
        return getPathParams().get("petId");
    }

    public String getStatus() {
        return getQueryParams().getOrDefault("status", null);
    }

    public static PetDataBuilder builder() {
        return new PetDataBuilder();
    }

    public static class PetDataBuilder extends RequestDataBuilder {
//        private String id;
//        private Object body;
//        private String status;

        public PetDataBuilder petId(String id) {
//            this.id = id;
            pathParam("petId", id);
            return this;
        }

        public PetDataBuilder petId(long id) {
            return petId(String.valueOf(id));
        }

//        public PetDataBuilder body(Object body) {
//            this.body = body;
//            return this;
//        }

        public PetDataBuilder status(String status) {
//            this.status = status;
            queryParam("status", status);
            return this;
        }

        public PetData build() {
//            return new PetData(id, body, status);
            return new PetData(body, queryParams, pathParams, headers);
        }
    }
}
