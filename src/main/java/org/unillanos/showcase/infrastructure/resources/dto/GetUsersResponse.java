package org.unillanos.showcase.infrastructure.resources.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetUsersResponse {
    Set<UserDto> users;
}
