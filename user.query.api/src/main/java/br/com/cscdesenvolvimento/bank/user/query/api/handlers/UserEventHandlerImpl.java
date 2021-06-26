package br.com.cscdesenvolvimento.bank.user.query.api.handlers;

import br.com.cscdesenvolvimento.bank.user.core.events.UserRegisteredEvent;
import br.com.cscdesenvolvimento.bank.user.core.events.UserRemovedEvent;
import br.com.cscdesenvolvimento.bank.user.core.events.UserUpdatedEvent;
import br.com.cscdesenvolvimento.bank.user.query.api.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("user-group")
@AllArgsConstructor
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
