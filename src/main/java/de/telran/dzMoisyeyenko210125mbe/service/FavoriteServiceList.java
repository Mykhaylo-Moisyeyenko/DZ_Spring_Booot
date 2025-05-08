package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Favorite;
import de.telran.dzMoisyeyenko210125mbe.pojo.Product;
import de.telran.dzMoisyeyenko210125mbe.pojo.Role;
import de.telran.dzMoisyeyenko210125mbe.pojo.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    public Favorite getById(Long id) {
        for (Favorite favorite : favoriteLocalStorage) {
            if (favorite.getFavoriteId().equals(id))
                return favorite;
        }
        return null;
    }

    @Override
    public Favorite create(Favorite newFavorite) {
        if (favoriteLocalStorage.add(newFavorite)) {
            return getById(newFavorite.getFavoriteId());
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

    @Override
    public void deleteById(Long id) {
        if (getById(id) == null) {
            throw new IllegalArgumentException("Объекта с таким Id не существует");
        }
        for (int i = 0; i < favoriteLocalStorage.size(); i++) {//удаление реализовано без итератора
            if (favoriteLocalStorage.get(i).getFavoriteId() == id) {
                favoriteLocalStorage.remove(i);
            }
        }
    }
}
