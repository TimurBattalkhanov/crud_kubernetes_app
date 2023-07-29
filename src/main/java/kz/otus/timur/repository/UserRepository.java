package kz.otus.timur.repository;

import kz.otus.timur.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.id = ?1")
    Optional<UserEntity> getUserById(Long id);
}
