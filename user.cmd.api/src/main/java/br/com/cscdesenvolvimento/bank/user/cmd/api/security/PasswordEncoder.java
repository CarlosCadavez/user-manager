package br.com.cscdesenvolvimento.bank.user.cmd.api.security;

public interface PasswordEncoder {

    String hashPassword(String password);
}
