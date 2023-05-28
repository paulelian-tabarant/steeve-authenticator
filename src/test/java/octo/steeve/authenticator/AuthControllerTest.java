package octo.steeve.authenticator;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import octo.steeve.authenticator.controllers.AuthRequestBody;
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

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("should return a 32 chars token when provided existing name and password")
    void passingCase() {
        var requestBody = new AuthRequestBody("dertex", "killer");

        try {
            mockMvc.perform(post("/api/auth", toJson(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token", hasLength(32)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("should return 401 if the user does not exist")
    void userDoesNotExist() {
        var requestBody = new AuthRequestBody("gilbert", "killer");

        try {
            mockMvc.perform(post("/api/auth", toJson(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("should return 401 if name is missing")
    void missingName() {
        var requestBody = new RequestBodyWithMissingName("a-password");

        try {
            mockMvc.perform(post("/api/auth", toJson(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("should return 401 if password is missing")
    void missingPassword() {
        var requestBody = new RequestBodyWithMissingPassword("a name");

        try {
            mockMvc.perform(post("/api/auth", toJson(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static MockHttpServletRequestBuilder post(String url, String requestBody) {
        return MockMvcRequestBuilders.post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody);
    }

    private static String toJson(Object requestBody) {
        var gson = new Gson();
        return gson.toJson(requestBody);
    }
}