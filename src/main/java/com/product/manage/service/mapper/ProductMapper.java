package com.product.manage.service.mapper;

import com.product.manage.repository.model.Product;
import com.product.manage.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(ProductDto productDTO);

    ProductDto toDTO(Product product);

    @Mapping(target = "id", ignore = true) // On ignore l'ID pour ne pas écraser celui existant
    @Mapping(target = "createdAt", ignore = true) // On ignore createdAt pour ne pas le modifier
    @Mapping(target = "updatedAt", ignore = true) // updatedAt sera géré dans le service
    void updateEntityFromDTO(ProductDto productDTO, @MappingTarget Product product);
}