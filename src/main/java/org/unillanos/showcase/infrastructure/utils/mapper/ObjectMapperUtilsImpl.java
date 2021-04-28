package org.unillanos.showcase.infrastructure.utils.mapper;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ObjectMapperUtilsImpl implements ObjectMapperUtils {
    @Autowired
    private final ModelMapper mapper;
    
    @Override
    public <T, D> D map(final T source, Class<D> destinationType) {
        return mapper.map(source, destinationType);
    }

    @Override
    public <T, D> Set<D> mapAll(Collection<T> sourceList, Class<D> destinationType) {
        return sourceList.stream()
        .map(source -> mapper.map(source, destinationType))
        .collect(Collectors.toSet());
    }
    
}
