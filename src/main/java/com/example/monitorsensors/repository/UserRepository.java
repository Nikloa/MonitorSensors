package com.example.monitorsensors.repository;

import com.example.monitorsensors.model.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.login = :username")
    public UserEntity getUserByUsername(@Param("username") String login);
}
