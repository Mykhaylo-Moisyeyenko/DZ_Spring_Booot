package de.telran.dzMoisyeyenko210125mbe.pojo;

import java.util.Objects;

public class Category {

    private Long categeryId;

    private String name;

    public Category() {}

    public Category(Long categeryId, String name) {
        this.categeryId = categeryId;
        this.name = name;
    }

    public Long getCategeryId() {
        return categeryId;
    }

    public void setCategeryId(Long categeryId) {
        this.categeryId = categeryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categeryId=" + categeryId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return Objects.equals(categeryId, that.categeryId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categeryId, name);
    }
}
