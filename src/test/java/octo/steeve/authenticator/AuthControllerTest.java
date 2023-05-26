package octo.steeve.authenticator;

import com.google.gson.Gson;
import octo.steeve.authenticator.controllers.AuthRequestBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
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
        var requestBody = new AuthRequestBody( "dertex", "killer" );
        String jsonRequestBody = toJson(requestBody);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/auth").content(jsonRequestBody))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.token").isString())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.token", hasLength(32)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toJson(AuthRequestBody requestBody) {
        var gson = new Gson();
        return gson.toJson(requestBody);
    }
}