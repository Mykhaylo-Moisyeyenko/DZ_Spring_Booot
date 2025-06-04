package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.mapper.Mappers;
import de.telran.dzMoisyeyenko210125mbe.model.dto.ProductDto;
import de.telran.dzMoisyeyenko210125mbe.model.entity.ProductEntity;
import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import de.telran.dzMoisyeyenko210125mbe.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceList implements StorageServiceInterface<ProductDto, Long> {

    private final ProductRepository productRepository;
    private final Mappers mappers;

    private List<Product> localStorage = new ArrayList<>();//старый репозиторий, до подключения productRepository

    //инициализация бинов - была вынесена в класс DataInitializer

    @Override
    public ProductDto getById(Long id) throws Exception {
        Optional<ProductEntity> returnProductOptional = productRepository.findById(id);
        ProductEntity returnProductEntity = returnProductOptional.
                orElseThrow(()->new EntityNotFoundException("Product with ID " + id + " not found."));
        return mappers.convertProductEntityToProductDto(returnProductEntity);
    }

    @Override
    public List<ProductDto> getAll() {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (ProductEntity productEntity: productEntities){
            productDtos.add(mappers.convertProductEntityToProductDto(productEntity));
        }
        return productDtos;
    }

    @Override
    public ProductDto create(ProductDto newProductDto){
        if (newProductDto.getProductId() !=null){
            throw new IllegalArgumentException("Присвоение Id на данном этапе недопустимо");
        }
        ProductEntity productEntity = mappers.convertProductDtoToProductEntity(newProductDto);
        productEntity = productRepository.save(productEntity);

        ProductDto productDto = mappers.convertProductEntityToProductDto(productEntity);
        return productDto;
    }








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

    //1. Напишите методы в Репозитории для редактирования цены товара и дисконта в таблице Product c использованием @Query.
    public ProductDto updatePriceAndDiscountOfProduct(Long id, Double newPrice, Double newDiscountPrice) {
        int changedRows = productRepository.setPriceAndDiscountOfProduct(id, newPrice, newDiscountPrice);
        if (changedRows == 0) throw new IllegalStateException("Обновление не было выполнено!");
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Продукта с таким Id не существует"));
        return ProductDto.builder()
                .productId(productEntity.getProductId())
                .name(productEntity.getName())
                .imageUrl(productEntity.getImageUrl())
                .price(productEntity.getPrice())
                .discountPrice(productEntity.getDiscountPrice())
                .description(productEntity.getDescription())
                .categoryId(productEntity.getCategory().getName())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .build();
    }

    //    1) * напишите метод, позволяющий найти продукты, в описании которых (Description)
//    есть слово "Распродажа" и цена которых более 100 евро.
    public List<ProductDto> getByDescriptionContainingAndPriceGreaterThan(String description, Double price){
        List<ProductEntity> productEntities = productRepository.findByDescriptionContainingAndPriceGreaterThan(description, price);
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

    //    2) ** напишите метод, позволяющий найти продукты, категорию которых мы можем задать через
//    первый аргумент метода и которые имеют дисконтную цену (DiscountPrice > 0).
    public List<ProductDto> getByCategoryNameAndDiscountPriceGreaterThan(String categoryName, Double discountPrice) {
        List<ProductEntity> productEntities = productRepository.findByCategoryNameAndDiscountPriceGreaterThan(categoryName, discountPrice);
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

