package br.com.cscdesenvolvimento.bank.user.cmd.api.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RemoveUserCommand {

    @TargetAggregateIdentifier
    private String id;
}
