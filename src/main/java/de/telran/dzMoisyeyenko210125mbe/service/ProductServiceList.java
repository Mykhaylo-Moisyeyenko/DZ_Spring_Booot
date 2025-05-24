package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.model.dto.ProductDto;
import de.telran.dzMoisyeyenko210125mbe.model.entity.ProductEntity;
import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import de.telran.dzMoisyeyenko210125mbe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceList implements StorageServiceInterface<ProductDto, Long> {

    private final ProductRepository productRepository;

    private List<Product> localStorage = new ArrayList<>();

        //инициализация бинов - вынесена в класс DataInitializer

    @Override
    public List<ProductDto> getByName(String valueName) {
        List<ProductEntity> returnProductsEntity = productRepository.findByName(valueName);

        List<ProductDto> productDtos =
                returnProductsEntity.stream()
                        .map(productEntity ->
                                ProductDto.builder()
                                        .productId(productEntity.getProductId())
                                        .name(productEntity.getName())
                                        .imageUrl(productEntity.getImageUrl())
                                        .price(productEntity.getPrice())
                                        .discountPrice(productEntity.getDiscountPrice())
                                        .description(productEntity.getDescription())
                                        .categoryId(productEntity.getCategory().getName())
                                        .createdAt(productEntity.getCreatedAt())
                                        .updatedAt(productEntity.getUpdatedAt())
                                        .build()
                        ).collect(Collectors.toList());

        return productDtos;
    }


    public List<ProductDto> findByDiscount() {
        List<ProductEntity> productEntities = productRepository.findByDiscount();
        return productEntities.stream()
                .map(productEntity -> ProductDto.builder()
                        .productId(productEntity.getProductId())
                        .name(productEntity.getName())
                        .imageUrl(productEntity.getImageUrl())
                        .price(productEntity.getPrice())
                        .discountPrice(productEntity.getDiscountPrice())
                        .description(productEntity.getDescription())
                        .categoryId(productEntity.getCategory().getName())
                        .createdAt(productEntity.getCreatedAt())
                        .updatedAt(productEntity.getUpdatedAt())
                        .build()
                ).collect(Collectors.toList());
    }








    //Ниже - методы-заглушки, пока не реализованные:

    @Override
    public List<ProductDto> getAll() {
        return List.of();
    }

    @Override
    public ProductDto getById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public ProductDto create(ProductDto entity) {
        return null;
    }

    @Override
    public ProductDto updateById(Long aLong, ProductDto entity) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws Exception {

    }

    @Override
    public ProductDto updatePart(Long aLong, ProductDto entity) throws Exception {
        return null;
    }

    @Override
    public ProductDto getByEmail(String valueEmail) {
        return null;
    }

    //ниже - методы для ДЗ до 23.05.2025 (без использования Dto):
//    @Override
//    public List<Product> getAll() {
//        return localStorage;
//    }
//
//    @Override
//    public Product getById(Long id) throws Exception {
//        for (Product product : localStorage) {
//            if (product.getProductId().equals(id))
//                return product;
//        }
//        throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
//    }
//
//    @Override
//    public Product create(Product newProduct) {
//        if (localStorage.add(newProduct)) {
//            try {
//                return getById(newProduct.getProductId());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Product updateById(Long id, Product updateProduct) {
//        for (int i = 0; i < localStorage.size(); i++) {
//            Product product = localStorage.get(i);
//            if (product.getProductId().equals(id)) {
//                localStorage.set(i, updateProduct);
//                System.out.println("Проведено обновление продукта c Id: " + id);
//                return localStorage.get(i);
//            }
//        }
//        System.out.println("При обновлении продукта, продукт с Id " + id + " не был обнаружен, поэтому в базу внесен новый продукт с таким Id");
//        return create(updateProduct);
//    }
//
//    @Override //обновляю только поле Name
//    public Product updatePart(Long id, Product updatePart) throws Exception {
//        for (Product updatedPart : localStorage) {
//            if(updatedPart.getProductId().equals(id)) {
//                if (!updatedPart.getName().equals(updatePart.getName()))
//                    updatedPart.setName(updatePart.getName());
//                return updatedPart;
//            }
//        }
//        throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
//    }
//
//    @Override
//    public Product getByEmail(String valueEmail) {
//        return null;
//    }
//
//    @Override
//    public void deleteById(Long id) throws Exception {
//        if (getById(id) == null) {
//            throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
//        }
//        for (int i = 0; i < localStorage.size(); i++) {//удаление реализовано без итератора
//            if (localStorage.get(i).getProductId() == id) {
//                localStorage.remove(i);
//            }
//        }
//    }
}

