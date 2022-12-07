package com.vykhovanok.manager.converter;

import com.vykhovanok.manager.converter.mark.Convertible;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class DtoConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public DtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T, D extends Convertible> D convertToEntity(T dto, D entity) {
        return modelMapper.map(dto, (Type) entity.getClass());
    }

    public <T, D extends Convertible> T convertToDto(D entity, Type dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <T, D extends Convertible> D convertFromDtoToDto(T dtoConverted, D dtoConverting) {
        return modelMapper.map(dtoConverted, (Type) dtoConverting.getClass());
    }
}
