package org.unillanos.showcase.infrastructure.resources.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unillanos.showcase.application.service.service.RoleService;
import org.unillanos.showcase.domain.Role;
import org.unillanos.showcase.infrastructure.resources.dto.RoleDto;
import org.unillanos.showcase.infrastructure.utils.mapper.ObjectMapperUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/roles")
@RestController
public class RoleController {
    @Autowired
    private final RoleService roleService;
    @Autowired
    private final ObjectMapperUtils mapper;

    @GetMapping
    public ResponseEntity<List<RoleDto>> findAll() {
        var roles = roleService.findAll();
        return ResponseEntity.ok(mapper.mapAll(roles, RoleDto.class));

    }
    @PostMapping
    public ResponseEntity<RoleDto> save(@RequestBody @Valid RoleDto roleDto, BindingResult result) {
        if (!result.hasErrors()) {
            return roleService.save(mapper.map(roleDto, Role.class))
            .right()
            .map(role -> ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(role, RoleDto.class))).getOrElseGet(msg -> {
                log.warn(msg);
                return ResponseEntity.status(HttpStatus.CONFLICT).build();

            });
            
        }
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    
    

}
