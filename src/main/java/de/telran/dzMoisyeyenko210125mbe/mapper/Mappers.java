package de.telran.dzMoisyeyenko210125mbe.mapper;

import de.telran.dzMoisyeyenko210125mbe.model.dto.ProductDto;
import de.telran.dzMoisyeyenko210125mbe.model.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mappers {
    private final ModelMapper modelMapper;

    public ProductDto convertProductEntityToProductDto(ProductEntity productEntity){
        ProductDto productDto = modelMapper.map(productEntity, ProductDto.class);
        if (productEntity.getCategory() != null &&
                productEntity.getCategory().getCategoryId() != null){
            String id = productEntity.getCategory().getCategoryId().toString();
            productDto.setCategoryId(id);
        } else productDto.setCategoryId(null);
        return productDto;
    }

    public ProductEntity convertProductDtoToProductEntity(ProductDto productDto){
        ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);
        productEntity.setCategory(null);
        return productEntity;
    }











}
