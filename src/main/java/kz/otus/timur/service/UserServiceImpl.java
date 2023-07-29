package kz.otus.timur.service;

import jakarta.transaction.Transactional;
import kz.otus.timur.dto.UserDto;
import kz.otus.timur.handler.exception.UserAlreadyExistsException;
import kz.otus.timur.handler.exception.UserNotFoundException;
import kz.otus.timur.model.UserEntity;
import kz.otus.timur.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public UserDto getUserById(Long id) {
        return convertToDto(repository.getUserById(id).orElseThrow(() -> new UserNotFoundException("User with id=" + id + " not found")));
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public UserDto updateUserById(UserDto dto) {
        UserEntity user = repository.getUserById(dto.getId()).orElseThrow(() -> new UserNotFoundException("User with id=" + dto.getId() + " not found"));
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        return convertToDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        try {
            UserEntity savedUser = repository.save(convertToEntity(userDto));
            return convertToDto(savedUser);
        } catch (Exception psqle) {
            throw new UserAlreadyExistsException(psqle.getMessage());
        }
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = repository.findAll();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(entity -> dtos.add(convertToDto(entity)));
        return dtos;
    }

    private UserDto convertToDto(UserEntity user) {
        return new UserDto(user.getId(), user.getLogin(), user.getName());
    }

    private UserEntity convertToEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setLogin(dto.getLogin());
        entity.setName(dto.getName());
        return entity;
    }
}
