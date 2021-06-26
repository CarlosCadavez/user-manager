package br.com.cscdesenvolvimento.bank.user.cmd.api.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {

    private static final int ENCODE_STRENTH = 12;

    @Override
    public String hashPassword(String password) {
        var encoder = new BCryptPasswordEncoder(ENCODE_STRENTH);
        var hashedPassword = encoder.encode(password);

        return hashedPassword;
    }
}
