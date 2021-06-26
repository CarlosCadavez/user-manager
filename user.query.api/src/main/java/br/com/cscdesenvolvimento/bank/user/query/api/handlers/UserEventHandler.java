package br.com.cscdesenvolvimento.bank.user.query.api.handlers;

import br.com.cscdesenvolvimento.bank.user.core.events.UserRegisteredEvent;
import br.com.cscdesenvolvimento.bank.user.core.events.UserRemovedEvent;
import br.com.cscdesenvolvimento.bank.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {

    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
