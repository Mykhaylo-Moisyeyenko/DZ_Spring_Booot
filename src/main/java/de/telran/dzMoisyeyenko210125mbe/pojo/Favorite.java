package de.telran.dzMoisyeyenko210125mbe.pojo;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Favorite {

    private Long favoriteId;
    private User user;
    private Product product;

    private Favorite(){}

    public Favorite(Long favoriteId, User user, Product product) {
        this.favoriteId = favoriteId;
        this.user = user;
        this.product = product;
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "favoriteId=" + favoriteId +
                ", user=" + user +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return Objects.equals(favoriteId, favorite.favoriteId) && Objects.equals(user, favorite.user) && Objects.equals(product, favorite.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoriteId, user, product);
    }
}
