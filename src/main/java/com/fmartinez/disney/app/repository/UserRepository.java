package com.fmartinez.disney.app.repository;

import com.fmartinez.disney.app.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
}
