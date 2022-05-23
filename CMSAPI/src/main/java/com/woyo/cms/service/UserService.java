package com.woyo.cms.service;

import com.woyo.cms.DTO.UserDTO;
import com.woyo.cms.model.request.CreateUserRequestModel;
import com.woyo.cms.model.request.UserLoginRequestModel;

public interface UserService {
    UserDTO createUser(CreateUserRequestModel userRequestModel);
    UserDTO userLogin(UserLoginRequestModel userLoginRequestModel);
    UserDTO getUserById(int userId);
}
