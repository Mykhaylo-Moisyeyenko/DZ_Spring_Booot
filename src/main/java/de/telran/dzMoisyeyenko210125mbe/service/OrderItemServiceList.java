package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Order;
import de.telran.dzMoisyeyenko210125mbe.pojo.OrderItem;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemServiceList implements StorageServiceInterface<OrderItem> {

    private List<OrderItem> orderItemLocalStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        OrderItem orderItem1 = new OrderItem();
        orderItemLocalStorage.add(orderItem1);
    }

    @Override
    public List<OrderItem> getAll() {
        return orderItemLocalStorage;
    }
}
