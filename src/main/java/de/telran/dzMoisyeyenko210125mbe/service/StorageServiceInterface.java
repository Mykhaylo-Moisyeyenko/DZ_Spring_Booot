package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.pojo.Cart;

import java.util.List;

public interface StorageServiceInterface<T> {

    public List<T> getAll();
}
