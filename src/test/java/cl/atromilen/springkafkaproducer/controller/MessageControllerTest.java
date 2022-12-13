package cl.atromilen.springkafkaproducer.controller;

import cl.atromilen.springkafkaproducer.event.MessageForEmailing;
import cl.atromilen.springkafkaproducer.service.KafkaProducerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    private static final String SAVE_URI = "/messages/save";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KafkaProducerService service;

    @Test
    void whenEmailAndMessageBodyArePresentInPayload_onSaveMessage_ReturnOk() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(SAVE_URI)
                        .content(asJsonString(createMessage()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    void whenEmailIsNotPresentInPayload_onSaveMessage_ReturnBadRequest() throws Exception {
        var payloadWithoutEmail = createMessage();
        payloadWithoutEmail.setEmail(null);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(SAVE_URI)
                                .content(asJsonString(payloadWithoutEmail))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_details[0].message")
                        .value("email is required and can't be null nor empty."));
    }

    @Test
    void whenMessageBodyIsNotPresentInPayload_onSaveMessage_ReturnBadRequest() throws Exception {
        var payloadWithoutMessageBody = createMessage();
        payloadWithoutMessageBody.setMessageBody(null);


        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(SAVE_URI)
                                .content(asJsonString(payloadWithoutMessageBody))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error_details[0].message")
                        .value("message_body is required and can't be null nor empty."));
    }

    private MessageForEmailing createMessage(){
        MessageForEmailing message = new MessageForEmailing();
        message.setEmail("mockemail@mock.mock");
        message.setMessageBody("A mock message body");
        return message;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
