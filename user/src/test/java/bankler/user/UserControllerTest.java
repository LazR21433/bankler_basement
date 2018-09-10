package bankler.user;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	private static final String USER_FIELD_FIRSTNAME = "firstName";
	private static final String USER_FIELD_LASTNAME = "lastName";
	private static final String USER_FIELD_EMAIL = "email";
	private static final String USER_FIELD_PASSWORD = "password";

	private static final String TEST_VALUE_FIRSTNAME = "testfirstname";
	private static final String TEST_VALUE_LASTNAME = "testlastname";
	private static final String TEST_VALUE_EMAIL = "test@test.com";
	private static final String TEST_VALUE_PASSWORD = "testpassword";

	private static final String RESPONSE_AUTH_FAILED = "Authentication failed!";

	@Autowired
	private MockMvc mvc;

	@Test
	public void testLoginWhenUserNotRegisteredThenReturnFailed() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user")
				.param(USER_FIELD_EMAIL, TEST_VALUE_EMAIL)
				.param(USER_FIELD_PASSWORD, TEST_VALUE_PASSWORD))
				.andExpect(MockMvcResultMatchers.status().isForbidden())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo(RESPONSE_AUTH_FAILED)));

		testRegisterWhenUserNotRegisteredThenReturnSuccess();
		testLoginWhenUserRegisteredThenReturnSuccess();
		testRegisterWhenUserAlreadyRegisteredThenReturnFailed();

	}

	public void testLoginWhenUserRegisteredThenReturnSuccess() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user")
				.param(USER_FIELD_EMAIL, TEST_VALUE_EMAIL)
				.param(USER_FIELD_PASSWORD, TEST_VALUE_PASSWORD))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.json("{\"" + USER_FIELD_FIRSTNAME + "\":\"" + TEST_VALUE_FIRSTNAME + "\",\""
								+ USER_FIELD_LASTNAME + "\":\"" + TEST_VALUE_LASTNAME + "\",\""
								+ USER_FIELD_EMAIL + "\":\"" + TEST_VALUE_EMAIL + "\"}"));
	}

	public void testRegisterWhenUserAlreadyRegisteredThenReturnFailed() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/user")
				.param(USER_FIELD_FIRSTNAME, TEST_VALUE_FIRSTNAME)
				.param(USER_FIELD_LASTNAME, TEST_VALUE_LASTNAME)
				.param(USER_FIELD_EMAIL, TEST_VALUE_EMAIL)
				.param(USER_FIELD_PASSWORD, TEST_VALUE_PASSWORD))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	public void testRegisterWhenUserNotRegisteredThenReturnSuccess() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/user")
				.param(USER_FIELD_FIRSTNAME, TEST_VALUE_FIRSTNAME)
				.param(USER_FIELD_LASTNAME, TEST_VALUE_LASTNAME)
				.param(USER_FIELD_EMAIL, TEST_VALUE_EMAIL)
				.param(USER_FIELD_PASSWORD, TEST_VALUE_PASSWORD))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.json("{\"" + USER_FIELD_FIRSTNAME + "\":\"" + TEST_VALUE_FIRSTNAME + "\",\""
								+ USER_FIELD_LASTNAME + "\":\"" + TEST_VALUE_LASTNAME + "\",\""
								+ USER_FIELD_EMAIL + "\":\"" + TEST_VALUE_EMAIL + "\"}"));
	}
}
