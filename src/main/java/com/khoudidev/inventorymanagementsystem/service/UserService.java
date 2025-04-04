package com.khoudidev.inventorymanagementsystem.service;

import com.khoudidev.inventorymanagementsystem.dto.LoginRequest;
import com.khoudidev.inventorymanagementsystem.dto.RegisterRequest;
import com.khoudidev.inventorymanagementsystem.dto.Response;
import com.khoudidev.inventorymanagementsystem.dto.UserDTO;
import com.khoudidev.inventorymanagementsystem.entity.User;

public interface UserService {

    Response registerUser(RegisterRequest registerRequest);

    Response loginUser(LoginRequest loginRequest);

    Response getAllUsers();

    User getCurrentLoggedInUser();

    Response updateUser(Long id, UserDTO userDTO);

    Response deleteUser(Long id);

    Response getUserTransactions(Long id);
}
