package com.fmartinez.disney.app.security.auth;

import com.fmartinez.disney.app.dto.AuthenticationResponse;
import com.fmartinez.disney.app.dto.LoginRegisterRequest;
import com.fmartinez.disney.app.model.UserAccount;
import com.fmartinez.disney.app.repository.UserRepository;
import com.fmartinez.disney.app.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.fmartinez.disney.app.util.Rol.USER;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;
    private final UserDetailsService userDetailsService;

    public AuthenticationResponse register(LoginRegisterRequest register) throws InstantiationException, IllegalAccessException {
        UserAccount user = UserAccount.builder()
                .email(register.getEmail())
                .password(encoder.encode(register.getPassword()))
                .rol(USER)
                .build();
        repository.save(user);

        UserDetails details = userDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtService.createToken(details);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse login(LoginRegisterRequest login) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        UserAccount userAccount = repository.findByEmail(login.getEmail()).orElseThrow();
        UserDetails details = userDetailsService.loadUserByUsername(userAccount.getEmail());
        String token = jwtService.createToken(details);
        return AuthenticationResponse.builder().token(token).build();
    }


}
