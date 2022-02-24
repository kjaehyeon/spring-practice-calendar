package com.example.springpracticecalendar.core.domain.entity;

import com.example.springpracticecalendar.core.dto.UserCreateReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity{

    private String name;
    private String email;
    private String password;
    private LocalDate birthday;

    public static User fromUserCreateReq(
            UserCreateReq userCreateReq,
            PasswordEncoder passwordEncoder
    ){
        return User.builder()
                .name(userCreateReq.getName())
                .email(userCreateReq.getEmail())
                .password(passwordEncoder.encode(userCreateReq.getPassword()))
                .birthday(userCreateReq.getBirthday())
                .build();
    }
    public boolean isMatch(PasswordEncoder passwordEncoder, String password){
        return passwordEncoder.matches(password, this.password);
    }
}
