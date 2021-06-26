package br.com.cscdesenvolvimento.bank.user.cmd.api.aggregates;

import br.com.cscdesenvolvimento.bank.user.cmd.api.commands.RegisterUserCommand;
import br.com.cscdesenvolvimento.bank.user.cmd.api.commands.RemoveUserCommand;
import br.com.cscdesenvolvimento.bank.user.cmd.api.commands.UpdateUserCommand;
import br.com.cscdesenvolvimento.bank.user.cmd.api.security.PasswordEncoder;
import br.com.cscdesenvolvimento.bank.user.cmd.api.security.PasswordEncoderImpl;
import br.com.cscdesenvolvimento.bank.user.core.events.UserRegisteredEvent;
import br.com.cscdesenvolvimento.bank.user.core.events.UserRemovedEvent;
import br.com.cscdesenvolvimento.bank.user.core.events.UserUpdatedEvent;
import br.com.cscdesenvolvimento.bank.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregate {

    @AggregateIdentifier
    private String id;
    private User user;
    private final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        passwordEncoder = new PasswordEncoderImpl();
        var user = command.getUser();
        user.setId(command.getId());
        var password = user.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);
        user.getAccount().setPassword(hashedPassword);
        var event = UserRegisteredEvent.builder()
                .id(command.getId())
                .user(user)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        var user = command.getUser();
        user.setId(command.getId());
        var password = user.getAccount().getPassword();
        var hashedPassword = passwordEncoder.hashPassword(password);
        user.getAccount().setPassword(hashedPassword);
        var event = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        var userRemovedEvent = new UserRemovedEvent();
        userRemovedEvent.setId(command.getId());
        AggregateLifecycle.apply(userRemovedEvent);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }

}
