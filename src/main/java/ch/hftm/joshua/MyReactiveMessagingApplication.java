package ch.hftm.joshua;

import ch.hftm.joshua.client.PurgoMalumClient;
import dto.TextMessage;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.reactive.messaging.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.stream.Stream;

@ApplicationScoped
public class MyReactiveMessagingApplication {

    @RestClient
    PurgoMalumClient purgoMalumClient;


    @Incoming("text-validation")
    @Outgoing("text-validation-response")
    @Blocking
    public Message<TextMessage> validateText(Message<TextMessage> message) {
        var payload = message.getPayload();
        System.out.println("Validating text for id:" + payload.getSourceId() + " text:" + payload.getText());
        message.getPayload().setText(purgoMalumClient.getValidatedText(payload.getText()));
        System.out.println("New Text is:" + message.getPayload().getText());
        message.getPayload().setIsValid(true);
        return message;
    }

}
