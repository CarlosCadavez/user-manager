package br.com.cscdesenvolvimento.bank.user.cmd.api.commands;

import br.com.cscdesenvolvimento.bank.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String id;
    private User user;
}
