package com.fmartinez.disney.app.model;

import com.fmartinez.disney.app.util.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "USERS")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username cannot be blank")
    @Email
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    private Boolean enable;

    @Enumerated(EnumType.STRING)
    private Rol rol;


    @PrePersist
    public void prePersist() {
        if (Objects.isNull(enable)) {
            enable = false;
        }
    }

}
