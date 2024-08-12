package constants;

import java.util.Map;

public class Constants {
//	public static String BASE_PATH = "https://petstore3.swagger.io/api/v3";
	public static final String BASE_PATH = "http://localhost:8080/api/v3";
	public static final String PET_PATH = "/pet";
	public static final String SINGLE_PET_PATH = "/pet/{petId}";
	public static final String PET_FIND_BY_STATUS_PATH = "/pet/findByStatus";
	public static final String PET_FIND_BY_TAG_PATH = "/pet/findByTag";
	public static final String SINGLE_PET_UPLOAD_IMAGE_PATH = "/pet/{petId}/uploadImage";
	public static final String STORE_PATH = "/store";
	public static final String STORE_INVENTORY_PATH = "/store/inventory";
	public static final String STORE_ORDER_PATH = "/store/order";
	public static final String SINGLE_STORE_ORDER_PATH = "/store/order/{orderId}";
	public static final String USER_PATH = "/user";
	public static final String USER_CREATE_WITH_LIST_PATH = "/user/createWithList";
	public static final String USER_LOGIN_PATH = "/user/login";
	public static final String USER_LOGOUT_PATH = "/user/logout";
	public static final String SINGLE_USER_PATH = "/user/{username}";
	public static final Map<String,String> HEADERS = Map.of("Accept", "application/json");

}
