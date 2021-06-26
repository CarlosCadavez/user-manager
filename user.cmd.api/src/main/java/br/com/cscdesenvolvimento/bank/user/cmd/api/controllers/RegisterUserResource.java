package br.com.cscdesenvolvimento.bank.user.cmd.api.controllers;

import br.com.cscdesenvolvimento.bank.user.cmd.api.commands.RegisterUserCommand;
import br.com.cscdesenvolvimento.bank.user.cmd.api.dto.RegisterUserResponse;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class RegisterUserResource {

    private final CommandGateway commandGateway;

    @PostMapping
    public ResponseEntity<RegisterUserResponse> createUser(@RequestBody RegisterUserCommand command) {
        command.setId(UUID.randomUUID().toString());
        try {
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(
                    RegisterUserResponse
                            .builder()
                            .message("User successfully registered")
                            .build(), HttpStatus.CREATED);
        } catch (Exception e) {
            RegisterUserResponse registerUserResponse = RegisterUserResponse
                    .builder()
                    .message("Error while processing register user request for id - " + command.getId())
                    .build();
            return new ResponseEntity<>(registerUserResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
