package br.com.cscdesenvolvimento.bank.user.cmd.api.commands;

import br.com.cscdesenvolvimento.bank.user.core.models.User;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RemoveUserCommand {

    @TargetAggregateIdentifier
    private String id;
    private User user;
}
