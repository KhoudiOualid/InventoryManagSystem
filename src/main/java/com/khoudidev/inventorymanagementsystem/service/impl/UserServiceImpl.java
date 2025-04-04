package com.khoudidev.inventorymanagementsystem.service.impl;

import com.khoudidev.inventorymanagementsystem.dto.LoginRequest;
import com.khoudidev.inventorymanagementsystem.dto.RegisterRequest;
import com.khoudidev.inventorymanagementsystem.dto.Response;
import com.khoudidev.inventorymanagementsystem.dto.UserDTO;
import com.khoudidev.inventorymanagementsystem.entity.User;
import com.khoudidev.inventorymanagementsystem.enums.UserRole;
import com.khoudidev.inventorymanagementsystem.exceptions.InvalidCredentialsException;
import com.khoudidev.inventorymanagementsystem.exceptions.NotFoundException;
import com.khoudidev.inventorymanagementsystem.repository.UserRepository;
import com.khoudidev.inventorymanagementsystem.security.JwtUtils;
import com.khoudidev.inventorymanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;

    @Override
    public Response registerUser(RegisterRequest registerRequest) {

        UserRole role =  UserRole.MANAGER;
        if(registerRequest.getRole() != null){
            role = registerRequest.getRole();
        }

        User userToSave = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phoneNumber(registerRequest.getPhoneNumber())
                .role(role)
                .build();

        userRepository.save(userToSave);

        return Response.builder()
                .status(200)
                .message("user created successfully")
                .build();
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("Email not found"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
           throw new InvalidCredentialsException("password does not match");
        }
        String token = jwtUtils.generateToken(user.getEmail());

        return Response.builder()
                .status(200)
                .message("user logged in successfully ")
                .role(user.getRole())
                .token(token)
                .expirationTime("6 month")
                .build();
    }

    @Override
    public Response getAllUsers() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));

        List<UserDTO> userDTOS = modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());

        userDTOS.forEach(user -> user.setTransactions(null));

        return Response.builder()
                .status(200)
                .message("success")
                .users(userDTOS)
                .build();
    }
    @Override
    public User getCurrentLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
         user.setTransactions(null);

        return user;
    }

    @Override
    public Response updateUser(Long id, UserDTO userDTO) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if(userDTO.getEmail() != null) existingUser.setEmail(userDTO.getEmail());
        if(userDTO.getName() != null) existingUser.setName(userDTO.getName());
        if(userDTO.getRole() != null) existingUser.setRole(userDTO.getRole());
        if(userDTO.getPhoneNumber() != null) existingUser.setPhoneNumber(userDTO.getPhoneNumber());

        if(userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()){
                existingUser.setPassword(passwordEncoder.encode(userDTO.getPassword()) );
        }

        userRepository.save(existingUser);

        return Response.builder()
                .status(200)
                .message("User Successfuly updated")
                .build();
    }

    @Override
    public Response deleteUser(Long id) {
            userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found"));
            userRepository.deleteById(id);



        return Response.builder()
                .status(200)
                .message("User Successfully Deleted")
                .build();
    }

    @Override
    public Response getUserTransactions(Long id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("User not found"));

            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTO.getTransactions().forEach(transactionDTO ->{
                transactionDTO.setUser(null);
                transactionDTO.setSupplier(null);
            });
        return Response.builder()
                .status(200)
                .message("success")
                .user(userDTO)
                .build();

            }



}
