package org.unillanos.showcase.infrastructure.utils.mapper;

import java.util.Collection;
import java.util.List;

public interface ObjectMapperUtils {
    <T, D> D map (final T source, Class<D> destinationType);
    <T, D> List<D> mapAll(Collection<T> sourceList, Class<D> destinationType);
}
