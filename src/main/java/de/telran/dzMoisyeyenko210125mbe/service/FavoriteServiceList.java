package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.exception.BadRequestException;
import de.telran.dzMoisyeyenko210125mbe.model.dto.FavoriteDto;
import de.telran.dzMoisyeyenko210125mbe.model.entity.FavoriteEntity;
import de.telran.dzMoisyeyenko210125mbe.model.entity.ProductEntity;
import de.telran.dzMoisyeyenko210125mbe.model.entity.UserEntity;
import de.telran.dzMoisyeyenko210125mbe.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteServiceList implements StorageServiceInterface<FavoriteDto, Long> {

    private final FavoriteRepository favoriteRepository;

    //инициализация бинов - вынесена в класс DataInitializer

    @Override
    public List<FavoriteDto> getAll() {
        List<FavoriteEntity> favoriteEntities = favoriteRepository.findAll();
        List<FavoriteDto> favoriteDtos = new ArrayList<>();
        for (FavoriteEntity favorite : favoriteEntities) {
            FavoriteDto favoriteDto = FavoriteDto.builder()
                    .favoriteId(favorite.getFavoriteId())
                    .userId(favorite.getUser().getUserId())
                    .productId(favorite.getProduct().getProductId())
                    .build();
            favoriteDtos.add(favoriteDto);
        }
        return favoriteDtos;
    }

    @Override
    public FavoriteDto getById(Long id) throws Exception {
        Optional<FavoriteEntity> returnFavoriteOptional = favoriteRepository.findById(id);
        FavoriteEntity returnFavoriteEntity = returnFavoriteOptional.orElse(new FavoriteEntity());

        FavoriteDto returnFavoriteDto = FavoriteDto.builder()
                .favoriteId(returnFavoriteEntity.getFavoriteId())
                .userId(returnFavoriteEntity.getUser().getUserId())
                .productId(returnFavoriteEntity.getProduct().getProductId())
                .build();
        return returnFavoriteDto;
    }

    @Override
    public FavoriteDto create(FavoriteDto newFavoriteDto) {
        if (newFavoriteDto.getFavoriteId()!= null)
            throw new IllegalArgumentException("Присвоение Id на данном этапе недопустимо");

        UserEntity userStub = UserEntity.builder()//создаю заглушку для юзера
                .userId(newFavoriteDto.getUserId())
                .build();

        ProductEntity productStub = ProductEntity.builder()//создаю заглушку для продукта
                .productId(newFavoriteDto.getProductId())
                .build();

        FavoriteEntity favoriteEntity = FavoriteEntity.builder()
                .user(userStub)//вставляю заглушку
                .product(productStub)//вставляю заглушку
                .build();
        favoriteEntity = favoriteRepository.save(favoriteEntity);

        FavoriteDto resultFavoriteDto = FavoriteDto.builder()
                .favoriteId(favoriteEntity.getFavoriteId())
                .userId(favoriteEntity.getUser().getUserId())
                .productId(favoriteEntity.getProduct().getProductId())
                .build();

        return resultFavoriteDto;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (getById(id) == null) {
            throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
        }
        favoriteRepository.deleteById(id);
    }


    //Прочие методы пока реализованы как заглушки:

    @Override
    public FavoriteDto updateById(Long aLong, FavoriteDto entity) {
        return null;
    }

    @Override
    public FavoriteDto updatePart(Long aLong, FavoriteDto entity) throws Exception {
        return null;
    }

    @Override
    public FavoriteDto getByEmail(String valueEmail) {
        return null;
    }

    @Override
    public List<FavoriteDto> getByName(String valueName) {
        return List.of();
    }
}
