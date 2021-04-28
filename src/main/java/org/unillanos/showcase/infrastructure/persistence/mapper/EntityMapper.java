package org.unillanos.showcase.infrastructure.persistence.mapper;

public interface EntityMapper {
    <T, D> T map(T source, Class<D> destinationClass);
    <T, D> T mapAll();
}
