package com.ecommerce.user.service;

import com.ecommerce.user.dto.AddressDto;
import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
   private final UserRepository userRepository;




    public List<UserResponse> FetchAllUsers(){
        return userRepository.findAll().stream().map(this::MapFromUserToUserResponse).collect(Collectors.toList());
    }
    public String addUser(UserRequest userRequest){
        User user = mapFromUserRequestToUser(userRequest);
       userRepository.save(user);
        return "User created successfully";
    }
    public User mapFromUserRequestToUser(UserRequest userRequest){
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if(userRequest.getAddressDto()!=null){
            Address address = new Address();
            address.setCity(userRequest.getAddressDto().getCity());
            address.setZipcode(userRequest.getAddressDto().getZipcode());
            address.setStreet(userRequest.getAddressDto().getStreet());
            address.setState(userRequest.getAddressDto().getState());
            address.setCountry(userRequest.getAddressDto().getCountry());
            user.setAddress(address);
        }
        return user;

    }
    public UserResponse MapFromUserToUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setId(String.valueOf(user.getId()));
        if(user.getAddress()!=null){
            AddressDto addressDto = new AddressDto();
            addressDto.setZipcode(user.getAddress().getZipcode());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setState(user.getAddress().getState());
            addressDto.setCountry(user.getAddress().getCountry());
            addressDto.setStreet(user.getAddress().getStreet());
            userResponse.setAddressDto(addressDto);
        }
        return userResponse;

    }

    public Optional<UserResponse> getUserSpecific(long id) {
        return userRepository.findById(id).map(this::MapFromUserToUserResponse);
    }

    public Boolean modifyUser(Long id,UserRequest updatedUserRequest) {
        User exsiting_user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("USER NOT FOUND"));
        exsiting_user.setFirstName(updatedUserRequest.getFirstName());
        exsiting_user.setLastName(updatedUserRequest.getLastName());
        userRepository.save(exsiting_user);
        return true;
    }
}
