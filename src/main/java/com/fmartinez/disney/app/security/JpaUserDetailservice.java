package com.fmartinez.disney.app.security;

import com.fmartinez.disney.app.model.UserAccount;
import com.fmartinez.disney.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
@RequiredArgsConstructor
public class JpaUserDetailservice implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();
        UserAccount userAccount = repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        return new User(userAccount.getEmail(),
                userAccount.getPassword(),
                userAccount.getEnable(),
                true,
                true,
                true,
                authorities);
    }
}
