package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Role;
import de.telran.dzMoisyeyenko210125mbe.pojo.User;
import jakarta.annotation.PostConstruct;
import de.telran.dzMoisyeyenko210125mbe.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceList implements StorageServiceInterface<User, Long>{

    private List<User> userLocalStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        User user1 = new User(1L, "Misha", "123@gmail.com", "3-44-22", "sdfs", Role.CLIENT);
        userLocalStorage.add(user1);
    }

    @Override
    public List<User> getAll() {
        return userLocalStorage;
    }

    @Override
    public User getById(Long id) throws Exception {
        for (User user : userLocalStorage) {
            if (user.getUserId().equals(id))
                return user;
        }
        throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
    }

    @Override
    public User create(User newUser) {
        if (userLocalStorage.add(newUser)) {
            try {
                return getById(newUser.getUserId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public User updateById(Long id, User updateUser) {
        for (int i = 0; i < userLocalStorage.size(); i++) {
            User user = userLocalStorage.get(i);
            if (user.getUserId().equals(id)) {
                userLocalStorage.set(i, updateUser);
                System.out.println("Проведено обновление Id: " + id);
                return userLocalStorage.get(i);
            }
        }
        System.out.println("При обновлении объект с Id " + id + " не был обнаружен, поэтому в базу внесен новый объект с таким Id");
        return create(updateUser);
    }

    @Override //обновляю только поле Name
    public User updatePart(Long id, User updatePart) throws Exception {
        for (User updatedPart : userLocalStorage) {
            if(updatedPart.getUserId().equals(id)) {
                if (!updatedPart.getName().equals(updatePart.getName()))
                    updatedPart.setName(updatePart.getName());
                return updatedPart;
            }
        }
        throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
    }

    @Override
    public void deleteById(Long id) throws Exception{
        if (getById(id) == null) {
            throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
        }
        for (int i = 0; i < userLocalStorage.size(); i++) {//удаление реализовано без итератора
            if (userLocalStorage.get(i).getUserId() == id) {
                userLocalStorage.remove(i);
            }
        }
    }
}
