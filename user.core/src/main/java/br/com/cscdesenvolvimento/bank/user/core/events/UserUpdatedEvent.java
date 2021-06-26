package br.com.cscdesenvolvimento.bank.user.core.events;

import br.com.cscdesenvolvimento.bank.user.core.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdatedEvent {

    private String id;
    private User user;
}
