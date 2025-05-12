package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class FavoriteServiceList implements StorageServiceInterface<Favorite, Long>{

    private List<Favorite> favoriteLocalStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        Product product1 = new Product();
        product1.setProductId(2L);
        product1.setName("Огурец");
        product1.setPrice(1.4);

        User user3 = new User(1L, "Pasha", "1234@gmail.com", "3-44-22", "sdfs", Role.CLIENT);

        Favorite favorite1 = new Favorite(1L, user3, product1);

        favoriteLocalStorage.add(favorite1);
    }

    @Override
    public List<Favorite> getAll() {
        return favoriteLocalStorage;
    }

    @Override
    public Favorite getById(Long id) throws Exception {
        for (Favorite favorite : favoriteLocalStorage) {
            if (favorite.getFavoriteId().equals(id))
                return favorite;
        }
        return null;
    }

    @Override
    public Favorite create(Favorite newFavorite) {
        if (favoriteLocalStorage.add(newFavorite)) {
            try {
                return getById(newFavorite.getFavoriteId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Favorite updateById(Long id, Favorite updateFavorite) {
        for (int i = 0; i < favoriteLocalStorage.size(); i++) {
            Favorite favorite = favoriteLocalStorage.get(i);
            if (favorite.getFavoriteId().equals(id)) {
                favoriteLocalStorage.set(i, updateFavorite);
                System.out.println("Проведено обновление Id: " + id);
                return favoriteLocalStorage.get(i);
            }
        }
        System.out.println("При обновлении объект с Id " + id + " не был обнаружен, поэтому в базу внесен новый объект с таким Id");
        return create(updateFavorite);
    }

    @Override //обновляю только поле Product
    public Favorite updatePart(Long id, Favorite updatePart) throws Exception {
        for (Favorite updatedPart : favoriteLocalStorage) {
            if(updatedPart.getFavoriteId().equals(id)) {
                if (!updatedPart.getProduct().equals(updatePart.getProduct()))
                    updatedPart.setProduct(updatePart.getProduct());
                return updatedPart;
            }
        }
        throw new NoSuchElementException("При update не нашли объект с id = "+id);
    }

    @Override
    public void deleteById(Long id) {
        try {
            if (getById(id) == null) {
                throw new IllegalArgumentException("Объекта с таким Id не существует");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < favoriteLocalStorage.size(); i++) {//удаление реализовано без итератора
            if (favoriteLocalStorage.get(i).getFavoriteId() == id) {
                favoriteLocalStorage.remove(i);
            }
        }
    }
}
