package utils.requestspecs;

import static constants.Constants.*;

import io.restassured.specification.RequestSpecification;
import utils.requestdata.RequestData;

public class UserRequestSpecs {

	public static RequestSpecification getUser(String username) {
		return BaseRequestSpecs.getRequestSpec(SINGLE_USER_PATH,
											   RequestData.userData()
														  .username(username)
														  .build());
	}

	public static RequestSpecification login(String username, String password) {
		return BaseRequestSpecs.getRequestSpec(USER_LOGIN_PATH,
											   RequestData.userData()
														  .usernameQueryParam(username)
														  .password(password)
														  .build());
	}
}
