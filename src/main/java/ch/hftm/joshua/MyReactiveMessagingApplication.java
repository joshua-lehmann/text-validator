package ch.hftm.joshua;

import dto.TextMessage;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.reactive.messaging.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.util.stream.Stream;

@ApplicationScoped
public class MyReactiveMessagingApplication {

    @Incoming("text-validation")
    @Outgoing("text-validation-response")
    public Message<TextMessage> validateText(Message<TextMessage> message) {
        var payload = message.getPayload();
        System.out.println("Validating text for id:" + payload.getSourceId() + " text:" + payload.getText());
        message.getPayload().setText(payload.getText().toUpperCase());
        message.getPayload().setIsValid(true);
        return message;
    }

}
