package org.example.messagesservice.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.messagesservice.api.exception.ApiException;
import org.example.messagesservice.common.ExceptionHandler;
import org.example.messagesservice.core.domain.Health;
import org.example.messagesservice.core.services.HealthService;
import org.example.messagesservice.core.services.exception.ServiceInternalException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HealthController.class)
public class HealthControllerTests {

    @Autowired
    private MockMvc server;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private HealthService healthService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ExceptionHandler<ApiException> handlerFactory() {
            return new ControllerExceptionHandler();
        }
    }

    @Test
    public void getHealth_cached_returnsHealth() throws Exception {
        var health = new Health("message-service", "OK", "1.0.0");
        when(healthService.getHealth()).thenReturn(health);
        server.perform(get("/health"))
            .andExpect(content().string(objectMapper.writeValueAsString(health)))
            .andExpect(status().isOk());
    }

    @Test
    public void getHealth_internalError_returns500() throws Exception {
        when(healthService.getHealth()).thenThrow(ServiceInternalException.class);
        server.perform(get("/health"))
            .andExpect(status().is5xxServerError());
    }
}
