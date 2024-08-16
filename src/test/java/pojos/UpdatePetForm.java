package pojos;

import java.util.HashMap;
import java.util.Map;

public class UpdatePetForm {

    private String petId;
    private String name;
    private String status;

    public UpdatePetForm(String petId, String name, String status) {
        this.petId = petId;
        this.name = name;
        this.status = status;
    }

    public UpdatePetForm() {
    }

    public static UpdatePetForm from(Map<String, String> formData) {
        return new UpdatePetForm(
                formData.getOrDefault("petId", null),
                formData.getOrDefault("name", null),
                formData.getOrDefault("status", null)
        );
    }

    public boolean matches(Pet pet) {
        if (pet == null) return false;
        return petId.equals(String.valueOf(pet.getId()))
                && name.equals(pet.getName())
                && (status == null || status.equals(pet.getStatus()));
    }

    public Map<String, String> getQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        if (name != null) {
            queryParams.put("name", name);
        }
        if (status != null) {
            queryParams.put("status", status);
        }
        return queryParams;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
