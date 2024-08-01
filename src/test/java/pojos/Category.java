package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Category{

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private long id;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Category category = (Category) o;
		return id == category.id && Objects.equals(name, category.name);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(name);
		result = 31 * result + Long.hashCode(id);
		return result;
	}
}