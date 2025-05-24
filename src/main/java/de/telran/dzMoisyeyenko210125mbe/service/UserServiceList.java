package de.telran.dzMoisyeyenko210125mbe.service;

import de.telran.dzMoisyeyenko210125mbe.exception.BadRequestException;
import de.telran.dzMoisyeyenko210125mbe.model.dto.UserDto;
import de.telran.dzMoisyeyenko210125mbe.model.entity.UserEntity;
import de.telran.dzMoisyeyenko210125mbe.model.enums.Role;
import de.telran.dzMoisyeyenko210125mbe.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceList implements StorageServiceInterface<UserDto, Long> {

    private final UserRepository userRepository;

    //инициализация бинов - вынесена в класс DataInitializer

    @Override
    public List<UserDto> getAll() {
        List<UserEntity> usersEntity = userRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();
        for (UserEntity user : usersEntity) {
            UserDto userDto = UserDto.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole().name())
                    .passwordHash("******")
                    .build();
            usersDto.add(userDto);
        }
        return usersDto;
    }

    @Override
    public UserDto getById(Long id) throws Exception {
        Optional<UserEntity> returnUserOptional = userRepository.findById(id);
        UserEntity returnUserEntity = returnUserOptional.orElse(new UserEntity());

        UserDto returnUserDto = UserDto.builder()
                .userId(returnUserEntity.getUserId())
                .name(returnUserEntity.getName())
                .email(returnUserEntity.getEmail())
                .phoneNumber(returnUserEntity.getPhoneNumber())
                .passwordHash("*************")
                .role(returnUserEntity.getRole().name())
                .build();
        return returnUserDto;
    }

    @Override
    public UserDto create(UserDto newUserDto) {
        if (newUserDto.getUserId() != null)
            throw new IllegalArgumentException("Присвоение Id на данном этапе недопустимо");

        UserEntity userEntity = UserEntity.builder()
                .name(newUserDto.getName())
                .phoneNumber(newUserDto.getPhoneNumber())
                .email(newUserDto.getEmail())
                .passwordHash(newUserDto.getPasswordHash())
                .role(Role.valueOf(newUserDto.getRole()))
                .build();

        userEntity = userRepository.save(userEntity);

        UserDto resultUserDto = UserDto.builder()
                .userId(userEntity.getUserId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .role(userEntity.getRole().name())
                .passwordHash("******")
                .build();

        return resultUserDto;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        if (getById(id) == null) {
            throw new BadRequestException("Объект c Id= " + id + " не найден!!!");
        }
        userRepository.deleteById(id);
    }

    public UserDto getByEmail(String valueEmail) {

        UserEntity returnUserEntity = userRepository.findByEmail(valueEmail);

        UserDto resultUserDto = UserDto.builder()
                .userId(returnUserEntity.getUserId())
                .name(returnUserEntity.getName())
                .email(returnUserEntity.getEmail())
                .phoneNumber(returnUserEntity.getPhoneNumber())
                .role(returnUserEntity.getRole().name())
                .passwordHash("******")
                .build();
        return resultUserDto;
    }

    public List<UserDto> getByName(String valueName) {
        List<UserEntity> returnUsersEntity = userRepository.findByName(valueName);

        List<UserDto> returnUsersDto =
                returnUsersEntity.stream()
                        .map(userEntity ->
                                UserDto.builder()
                                        .userId(userEntity.getUserId())
                                        .name(userEntity.getName())
                                        .email(userEntity.getEmail())
                                        .phoneNumber(userEntity.getPhoneNumber())
                                        .role(userEntity.getRole().name())
                                        .passwordHash("**************")
                                        .build())
                        .collect(Collectors.toList());

        return returnUsersDto;
    }


    //Прочие методы пока реализованы как заглушки:

    @Override
    public UserDto updateById(Long aLong, UserDto entity) {
        return null;
    }

    @Override
    public UserDto updatePart(Long aLong, UserDto entity) throws Exception {
        return null;
    }


}
