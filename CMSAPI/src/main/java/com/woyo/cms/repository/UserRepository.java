package com.woyo.cms.repository;

import com.woyo.cms.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
    @Query("SELECT u FROM UserModel u " +
            "WHERE u.email = :email")
    Optional<UserModel> findByEmail(String email);

    @Query("SELECT u FROM UserModel u " +
            "WHERE u.userName = :userName " +
            "AND u.password = :password " +
            "AND u.status = 'Y'")
    Optional<UserModel> findByUserNameAndPassword(String userName, String password);
}
