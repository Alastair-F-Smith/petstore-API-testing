package pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet{

	@JsonProperty("photoUrls")
	private List<String> photoUrls;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private long id;

	@JsonProperty("category")
	private Category category;

	@JsonProperty("tags")
	private List<TagsItem> tags;

	@JsonProperty("status")
	private String status;

	public static Pet fromDataTableRow(Map<String, String> row) {
		Pet pet = new Pet();
		pet.setPhotoUrls(new ArrayList<>());
		pet.setTags(new ArrayList<>());
		pet.setCategory(new Category());
		pet.setName(row.getOrDefault("name", ""));
		pet.setId(Long.parseLong(row.getOrDefault("id", "-1")));
		pet.setStatus(row.getOrDefault("status", "available"));
		return pet;
	}

	public void setPhotoUrls(List<String> photoUrls){
		this.photoUrls = photoUrls;
	}

	public List<String> getPhotoUrls(){
		return photoUrls;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getId(){
		return id;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setTags(List<TagsItem> tags){
		this.tags = tags;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Pet pet = (Pet) o;
		return id == pet.id && Objects.equals(photoUrls, pet.photoUrls) &&
				Objects.equals(name, pet.name) && Objects.equals(category, pet.category) &&
				Objects.equals(tags, pet.tags) && Objects.equals(status, pet.status);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(photoUrls);
		result = 31 * result + Objects.hashCode(name);
		result = 31 * result + Long.hashCode(id);
		result = 31 * result + Objects.hashCode(category);
		result = 31 * result + Objects.hashCode(tags);
		result = 31 * result + Objects.hashCode(status);
		return result;
	}
}