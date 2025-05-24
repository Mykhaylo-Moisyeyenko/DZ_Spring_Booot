package de.telran.dzMoisyeyenko210125mbe.repository;

import de.telran.dzMoisyeyenko210125mbe.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(nativeQuery = true, value = "select * from users as u where u.email = ?1")
    UserEntity findByEmail(String valueEmail);

    @Query(nativeQuery = true, value = "select * from users as u where u.name = ?1")
    List<UserEntity> findByName(String valueName);
}
