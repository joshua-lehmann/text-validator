# text-validator

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

# Purpose
Quarkus Project using reactive messaging and a kafka conenctor to validate incoming messages and send a response back to a different topic. Example usage can be found in [this project](https://github.com/joshua-lehmann/blog-service) in the `blogService.java`

## Explanation
This project is a text validation service built with Java, Quarkus, and Maven. It uses the MicroProfile Reactive Messaging API to process incoming messages, validate the text content, and send out responses. The project is configured to use SmallRye Kafka as the connector for both incoming and outgoing messages.

The main components of the project are:

- `TextMessage.java`: This is a data transfer object (DTO) that represents the text message to be validated. It contains the text to be validated, the source ID of the message, and a flag indicating whether the text is valid.

- `PurgoMalumClient.java`: This is a REST client interface for the [PurgoMalum text validation API](https://www.purgomalum.com/). It defines a method `getValidatedText` that sends a GET request to the PurgoMalum API with the text to be validated as a query parameter.

- `MyReactiveMessagingApplication.java`: This is the main application class. It defines a method `validateText` that is annotated with `@Incoming` and `@Outgoing` to receive and send messages. The method retrieves the text from the incoming message, sends it to the PurgoMalum service for validation, updates the message with the validated text, and sends the message out.

The project uses Quarkus for dependency injection and configuration. The URL of the PurgoMalum service is configured in the `application.properties` file.

This project demonstrates how to build a reactive application with MicroProfile and Quarkus, and how to integrate with an external REST service. It also shows how to use Lombok to simplify the creation of data classes in Java.


## Validation Process
The validation sends the incoming message to the PurgoMalum API and checks if the response contains any profanity. If the response contains profanity, the api returns a text where the profanity is replaced with asterisks. Then sends the message with id, new text and `isValid=true` to the `text-validation-response` topic. 