package de.telran.dzMoisyeyenko210125mbe.pojo;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Category {

    private Long categoryId;

    private String name;

    public Category() {}

    public Category(Long categeryId, String name) {
        this.categoryId = categeryId;
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categeryId) {
        this.categoryId = categeryId;
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
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return Objects.equals(categoryId, that.categoryId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId, name);
    }
}
