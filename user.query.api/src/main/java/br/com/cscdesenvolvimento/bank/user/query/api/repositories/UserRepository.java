package br.com.cscdesenvolvimento.bank.user.query.api.repositories;

import br.com.cscdesenvolvimento.bank.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
