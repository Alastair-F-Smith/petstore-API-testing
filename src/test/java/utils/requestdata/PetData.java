package utils.requestdata;

import java.util.HashMap;
import java.util.Map;

public class PetData extends RequestData {

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

        public PetDataBuilder petId(String id) {
            pathParam("petId", id);
            return this;
        }

        public PetDataBuilder petId(long id) {
            return petId(String.valueOf(id));
        }

        public PetDataBuilder status(String status) {
            queryParam("status", status);
            return this;
        }

        public PetData build() {
            return new PetData(body, queryParams, pathParams, headers);
        }
    }
}
