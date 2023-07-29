package kz.otus.timur.service;

import kz.otus.timur.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto getUserById(Long id);
    void deleteUserById(Long id);
    UserDto updateUserById(UserDto user);
    UserDto createUser(UserDto user);

    List<UserDto> getAllUsers();
}
