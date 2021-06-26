package br.com.cscdesenvolvimento.bank.user.cmd.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "RegisterUserResponseBuilder")
@JsonDeserialize(builder = RegisterUserResponse.RegisterUserResponseBuilder.class)
public class RegisterUserResponse {

    private final String message;
    private final String id;

    @JsonPOJOBuilder(withPrefix = "")
    public static class RegisterUserResponseBuilder {}
}

