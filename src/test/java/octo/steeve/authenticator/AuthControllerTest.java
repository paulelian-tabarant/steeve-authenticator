package octo.steeve.authenticator;

import com.google.gson.Gson;
import octo.steeve.authenticator.controllers.AuthRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasLength;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    public static final String apiUrl = "/api/auth";
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should return a 32 chars token when provided existing name and password")
    void passingCase() throws Exception {
        var requestBody = new AuthRequest("dertex", "killer");

        mockMvc.perform(post(apiUrl, requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.token").isString())
            .andExpect(MockMvcResultMatchers.jsonPath("$.token", hasLength(32)));
    }

    @Test
    @DisplayName("should return 401 if the user does not exist")
    void userDoesNotExist() throws Exception {
        var nonExistingUserRequest = new AuthRequest("gilbert", "killer");

        mockMvc.perform(post(apiUrl, nonExistingUserRequest))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @DisplayName("should return 401 if name is missing")
    void missingName() throws Exception {
        var requestBody = new NameMissingRequestBody("a password looking for its name");

        mockMvc.perform(post(apiUrl, requestBody))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @DisplayName("should return 401 if password is missing")
    void missingPassword() throws Exception {
        var requestBody = new PasswordMissingRequestBody("a name looking for its password");

        mockMvc.perform(post(apiUrl, requestBody))
            .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }


    private static MockHttpServletRequestBuilder post(String url, Object requestBody) {
        return MockMvcRequestBuilders.post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(requestBody));
    }

    private static String toJson(Object requestBody) {
        var gson = new Gson();
        return gson.toJson(requestBody);
    }
}