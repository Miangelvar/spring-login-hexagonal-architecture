package org.unillanos.showcase.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{
    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role;
    private boolean active;
    private LocalDateTime createdDate;
}
