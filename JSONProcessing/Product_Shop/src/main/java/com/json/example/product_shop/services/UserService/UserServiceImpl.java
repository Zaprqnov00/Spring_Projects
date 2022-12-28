package com.json.example.product_shop.services.UserService;

import com.json.example.product_shop.domain.dtos.user.UserWithSoldProductsDTO;
import com.json.example.product_shop.domain.entities.User;
import com.json.example.product_shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.json.example.product_shop.constants.Utils.MODEL_MAPPER;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<UserWithSoldProductsDTO> getUsersWithSoldProducts() {

        List<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts();

       return allWithSoldProducts
                .stream()
                .map(user -> MODEL_MAPPER.map(user, UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());
    }
}
