package de.telran.dzMoisyeyenko210125mbe.repository;

import de.telran.dzMoisyeyenko210125mbe.model.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest()
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryTest;

    private UserEntity userEntityTemplate1;
    private UserEntity userEntityTemplate2;

    @BeforeEach
    void setUp() {
        userEntityTemplate1 = UserEntity.builder()
                .name("Misha")
                .email("aaa@gmail.com")
                .build();
        userEntityTemplate1 = userRepositoryTest.save(userEntityTemplate1);

        userEntityTemplate2 = UserEntity.builder()
                .name("Tim")
                .email("bbbbbbbbbb@gmail.com")
                .build();
        userEntityTemplate2 = userRepositoryTest.save(userEntityTemplate2);
    }

    @AfterEach
    void tearDown() {
        userRepositoryTest.delete(userEntityTemplate1);
        userRepositoryTest.delete(userEntityTemplate2);
    }

    @Test
    void findByEmailTest() {
        String emailExpected = "aaa@gmail.com";

        UserEntity userEntityReturn = userRepositoryTest.findByEmail(emailExpected);

        assertNotNull(userEntityReturn);
        assertEquals(emailExpected, userEntityReturn.getEmail());
    }

    @Test
    void findByNameTest() {
        String nameExpected = "Tim";

        List<UserEntity> userEntityListReturn = userRepositoryTest.findByName(nameExpected);

        assertNotNull(userEntityListReturn);
        assertEquals(1, userEntityListReturn.size());
        assertEquals(nameExpected, userEntityListReturn.getFirst().getName());
    }
}