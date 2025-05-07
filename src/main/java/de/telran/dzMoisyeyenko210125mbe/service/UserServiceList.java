package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Order;
import de.telran.dzMoisyeyenko210125mbe.pojo.Role;
import de.telran.dzMoisyeyenko210125mbe.pojo.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceList implements StorageServiceInterface<User>{

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
}
