package com.woyo.cms.service.impl;

import com.woyo.cms.DTO.UserDTO;
import com.woyo.cms.model.UserModel;
import com.woyo.cms.model.request.CreateUserRequestModel;
import com.woyo.cms.model.request.UserLoginRequestModel;
import com.woyo.cms.repository.UserRepository;
import com.woyo.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(CreateUserRequestModel userRequestModel) {
        UserModel user = new UserModel();
        user.setFirstName(userRequestModel.getFirstName());
        user.setLastName(userRequestModel.getLastName());
        user.setEmail(userRequestModel.getEmail());
        user.setUserName(userRequestModel.getUserName());
        user.setPassword(userRequestModel.getPassword());
        user.setStatus("Y");
        user.setCreatedAt(LocalDateTime.now());

        return convertUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO userLogin(UserLoginRequestModel userLoginRequestModel) {
        Optional<UserModel> userExists = userRepository.findByUserNameAndPassword(userLoginRequestModel.getUsername(), userLoginRequestModel.getPassword());

        if (userExists.isPresent()) {
            return convertUserDTO(userExists.get());
        } else {
            return null;
        }
    }

    @Override
    public UserDTO getUserById(int userId) {
        Optional<UserModel> userExists = userRepository.findById(userId);

        if (userExists.isPresent()) {
            return convertUserDTO(userExists.get());
        } else {
            return null;
        }
    }

    public UserDTO convertUserDTO(UserModel item) {
        return new UserDTO(
                item.getUserId(), item.getFirstName(), item.getLastName(),
                item.getEmail(), item.getUserName()
        );
    }
}
