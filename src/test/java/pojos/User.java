package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

public class User{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("userStatus")
	private int userStatus;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("id")
	private int id;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;

	public static User from(Map<String, String> dataTableRow) {
		User user = new User();
		user.setUsername(dataTableRow.get("username"));
		user.setPassword(dataTableRow.get("password"));
		user.setEmail(dataTableRow.get("email"));
		user.setFirstName(dataTableRow.get("firstName"));
		user.setLastName(dataTableRow.get("lastName"));
		user.setId(Integer.parseInt(dataTableRow.get("id")));
		user.setPhone(dataTableRow.get("phoneNumber"));
		user.setUserStatus(Integer.parseInt(dataTableRow.get("userStatus")));
		return user;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setUserStatus(int userStatus){
		this.userStatus = userStatus;
	}

	public int getUserStatus(){
		return userStatus;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;
		return userStatus == user.userStatus && id == user.id && Objects.equals(firstName, user.firstName) &&
				Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) &&
				Objects.equals(phone, user.phone) && Objects.equals(email, user.email) &&
				Objects.equals(username, user.username);
	}

	@Override
	public int hashCode() {
		int result = Objects.hashCode(firstName);
		result = 31 * result + Objects.hashCode(lastName);
		result = 31 * result + Objects.hashCode(password);
		result = 31 * result + userStatus;
		result = 31 * result + Objects.hashCode(phone);
		result = 31 * result + id;
		result = 31 * result + Objects.hashCode(email);
		result = 31 * result + Objects.hashCode(username);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", password='" + password + '\'' +
				", userStatus=" + userStatus +
				", phone='" + phone + '\'' +
				", id=" + id +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}