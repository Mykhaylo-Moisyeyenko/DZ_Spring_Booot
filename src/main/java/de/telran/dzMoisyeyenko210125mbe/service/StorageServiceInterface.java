package de.telran.dzMoisyeyenko210125mbe.service;

import java.util.List;

public interface StorageServiceInterface<T, ID> {

    List<T> getAll();

    T getById(ID id) throws Exception;

    T create(T entity);

    T updateById(ID id, T entity);

    void deleteById (ID id) throws Exception;

    T updatePart(ID id, T entity) throws Exception;

    T getByEmail(String valueEmail);

    List<T> getByName(String valueName);
}
