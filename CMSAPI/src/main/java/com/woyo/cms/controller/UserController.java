package com.woyo.cms.controller;

import com.woyo.cms.DTO.UserDTO;
import com.woyo.cms.model.request.CreateUserRequestModel;
import com.woyo.cms.model.request.UserLoginRequestModel;
import com.woyo.cms.response.DataResponse;
import com.woyo.cms.response.HandlerResponse;
import com.woyo.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/v1/user", produces = {"application/json"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(HttpServletResponse response, @RequestBody CreateUserRequestModel userRequestModel) {
        UserDTO userDTO = userService.createUser(userRequestModel);

        DataResponse<UserDTO> data = new DataResponse<>();
        data.setData(userDTO);
        HandlerResponse.responseSuccessCreatedWithData(response, data);
    }

    @PostMapping("/auth/login")
    public void userLogin(HttpServletResponse response, @RequestBody UserLoginRequestModel userLoginRequestModel) {
        UserDTO userDTO = userService.userLogin(userLoginRequestModel);

        if (userDTO != null) {
            DataResponse<UserDTO> data = new DataResponse<>();
            data.setData(userDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseBadRequest(response, "400", "Username or Password is wrong");
        }
    }

    @GetMapping("/{userId}")
    public void getUserById(HttpServletResponse response, @PathVariable int userId) {
        UserDTO userDTO = userService.getUserById(userId);

        if (userDTO != null) {
            DataResponse<UserDTO> data = new DataResponse<>();
            data.setData(userDTO);
            HandlerResponse.responseSuccessWithData(response, data);
        } else {
            HandlerResponse.responseNotFoundRequest(response, "404", "User not found");
        }

    }
}
