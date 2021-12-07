package com.banking.deserialzer;

import com.banking.bank.Customer;
import com.banking.bank.exception.CustomerAlreadyExistsException;
import com.banking.bank.exception.CustomerNotOwnerException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.IOException;

public class CustomerDeserializer extends JsonDeserializer<Customer> {

    @Override
    public Customer deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        String firstname = node.get("firstname").asText();
        String surname = node.get("surname").asText();
        String email = node.get("email").asText();
        String address = node.get("address").asText();
        String username = node.get("username").asText();
        String password = node.get("password").asText();

        return new Customer(firstname, surname, email, address, username, password);
    }
}