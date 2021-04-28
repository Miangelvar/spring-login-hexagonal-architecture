package org.unillanos.showcase.infrastructure.persistence.jpa.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = true, unique = true)
    private String name;
    @Column(nullable = true, unique = true)
    private String description;

    @OneToMany(mappedBy = "role")
    private Set<UserEntity> users;
}
