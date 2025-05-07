package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceList implements StorageServiceInterface<Order>{

    private List<Order> orderLocalStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        Order order1 = new Order();
        orderLocalStorage.add(order1);
    }

    @Override
    public List<Order> getAll() {
        return orderLocalStorage;
    }

}
