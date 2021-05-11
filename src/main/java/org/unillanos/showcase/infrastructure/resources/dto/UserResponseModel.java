package org.unillanos.showcase.infrastructure.resources.dto;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel extends RepresentationModel<UserResponseModel> {
    private Long id;
    private String username;
    private String email;
    private boolean active;
    private String createdDate;
    private String roleName;
    private Link link;

}
