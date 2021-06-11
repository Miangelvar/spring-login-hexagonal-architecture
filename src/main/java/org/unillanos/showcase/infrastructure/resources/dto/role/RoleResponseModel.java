package org.unillanos.showcase.infrastructure.resources.dto.role;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponseModel extends RepresentationModel<RoleResponseModel>{
    private String name;
    private String description;
    private Link link;
}
