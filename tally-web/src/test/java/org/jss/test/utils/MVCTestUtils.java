package org.jss.test.utils;

import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.test.web.server.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Arrays;

public class MVCTestUtils {
    public static MockMvc mockMvc(Object controller) {
        StandaloneMockMvcBuilder mockMvcBuilder = MockMvcBuilders.standaloneSetup(controller);

        return mockMvcBuilder.build();
    }
}
