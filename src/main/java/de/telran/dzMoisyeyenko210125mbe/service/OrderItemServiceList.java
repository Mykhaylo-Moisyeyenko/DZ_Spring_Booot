package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class OrderItemServiceList implements StorageServiceInterface<OrderItem, Long> {

    private List<OrderItem> orderItemLocalStorage = new ArrayList<>();

    @PostConstruct
    void init(){
        User user1 = new User(1L, "Misha", "123@gmail.com", "3-44-22", "sdfs", Role.CLIENT);

        Timestamp createdNow = new Timestamp(System.currentTimeMillis());
        Status status = Status.NEW;
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
        Order order2 = new Order(1L, user1, createdNow, "Киев, ул.Фонарная, 7", "3-56-85", "Новая Почта", status, updatedAt);

        Product product1 = new Product();
        product1.setProductId(2L);
        product1.setName("Огурец");
        product1.setPrice(1.4);

        OrderItem orderItem1 = new OrderItem(1L, order2, product1, 1, 2.44);
        orderItemLocalStorage.add(orderItem1);
    }

    @Override
    public List<OrderItem> getAll() {
        return orderItemLocalStorage;
    }

    @Override
    public OrderItem getById(Long id) throws Exception {
        for (OrderItem orderItem : orderItemLocalStorage) {
            if (orderItem.getOrderItemId().equals(id))
                return orderItem;
        }
        return null;
    }

    @Override
    public OrderItem create(OrderItem newOrderItem) {
        if (orderItemLocalStorage.add(newOrderItem)) {
            try {
                return getById(newOrderItem.getOrderItemId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public OrderItem updateById(Long id, OrderItem updateOrderItem) {
        for (int i = 0; i < orderItemLocalStorage.size(); i++) {
            OrderItem orderItem = orderItemLocalStorage.get(i);
            if (orderItem.getOrderItemId().equals(id)) {
                orderItemLocalStorage.set(i, updateOrderItem);
                System.out.println("Проведено обновление Id: " + id);
                return orderItemLocalStorage.get(i);
            }
        }
        System.out.println("При обновлении объект с Id " + id + " не был обнаружен, поэтому в базу внесен новый объект с таким Id");
        return create(updateOrderItem);
    }

    @Override //обновляю только поле Product
    public OrderItem updatePart(Long id, OrderItem updatePart) throws Exception {
        for (OrderItem updatedPart : orderItemLocalStorage) {
            if(updatedPart.getOrderItemId().equals(id)) {
                if (!updatedPart.getProduct().equals(updatePart.getProduct()))
                    updatedPart.setProduct(updatePart.getProduct());
                return updatedPart;
            }
        }
        throw new NoSuchElementException("При update не нашли объект с id = "+id);
    }

    @Override
    public OrderItem getByEmail(String valueEmail) {
        return null;
    }

    @Override
    public List<OrderItem> getByName(String valueName) {
        return List.of();
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
        for (int i = 0; i < orderItemLocalStorage.size(); i++) {//удаление реализовано без итератора
            if (orderItemLocalStorage.get(i).getOrderItemId() == id) {
                orderItemLocalStorage.remove(i);
            }
        }
    }
}
