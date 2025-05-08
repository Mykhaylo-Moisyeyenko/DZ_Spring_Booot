package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceList implements StorageServiceInterface<Order, Long> {

    private List<Order> orderLocalStorage = new ArrayList<>();

    @PostConstruct
    void init() {
        User user1 = new User(1L, "Misha", "123@gmail.com", "3-44-22", "sdfs", Role.CLIENT);
        Timestamp createdNow = new Timestamp(System.currentTimeMillis());
        Status status = Status.NEW;
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
        Order order1 = new Order(1L, user1, createdNow, "Киев, ул.Фонарная, 7", "3-56-85", "Новая Почта", status, updatedAt);
        orderLocalStorage.add(order1);
    }

    @Override
    public List<Order> getAll() {
        return orderLocalStorage;
    }

    @Override
    public Order getById(Long id) {
        for (Order order : orderLocalStorage) {
            if (order.getOrderId().equals(id))
                return order;
        }
        return null;
    }

    @Override
    public Order create(Order newOrder) {
        if (orderLocalStorage.add(newOrder)) {
            return getById(newOrder.getOrderId());
        }
        return null;
    }

    @Override
    public Order updateById(Long id, Order updateOrder) {
        for (int i = 0; i < orderLocalStorage.size(); i++) {
            Order order = orderLocalStorage.get(i);
            if (order.getOrderId().equals(id)) {
                orderLocalStorage.set(i, updateOrder);
                System.out.println("Проведено обновление Id: " + id);
                return orderLocalStorage.get(i);
            }
        }
        System.out.println("При обновлении объект с Id " + id + " не был обнаружен, поэтому в базу внесен новый объект с таким Id");
        return create(updateOrder);
    }

    @Override
    public void deleteById(Long id) {
        if (getById(id) == null) {
            throw new IllegalArgumentException("Объекта с таким Id не существует");
        }
        for (int i = 0; i < orderLocalStorage.size(); i++) {//удаление реализовано без итератора
            if (orderLocalStorage.get(i).getOrderId() == id) {
                orderLocalStorage.remove(i);
            }
        }
    }
}
