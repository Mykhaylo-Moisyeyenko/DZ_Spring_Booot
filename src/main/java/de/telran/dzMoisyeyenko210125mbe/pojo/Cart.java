package de.telran.dzMoisyeyenko210125mbe.pojo;

import java.util.Objects;

public class Cart {

    private Long cartId;
    private User user;

    private Cart(){}

    public Cart(Long cartId, User user) {
        this.cartId = cartId;
        this.user = user;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId) && Objects.equals(user, cart.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, user);
    }
}
