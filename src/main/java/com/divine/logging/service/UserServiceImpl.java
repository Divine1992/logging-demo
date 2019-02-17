package com.divine.logging.service;

import com.divine.logging.dao.dto.Success;
import com.divine.logging.dao.dto.UserModel;
import com.divine.logging.dao.entity.User;
import com.divine.logging.dao.repository.UserRepository;
import com.divine.logging.exception.RestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Success create(UserModel userModel) {
        log.debug("Create new user with model {}", userModel);
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        userRepository.save(user);

        return Success.success();
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    @Override
    public UserModel update(UserModel userModel) {
        log.debug("Update user {}", userModel);
        Optional<User> optionalUser = userRepository.findByUsername(userModel.getUsername());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            BeanUtils.copyProperties(userModel, user);
            user = userRepository.save(user);
            BeanUtils.copyProperties(user, userModel);
            return userModel;
        }
        log.error("User {} is not present", userModel);
        throw new RestException("User is not present");
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    @Override
    public UserModel get(String username) {
        log.debug("Get user with username = {}", username);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        UserModel userModel = new UserModel();
        optionalUser.ifPresent(user -> BeanUtils.copyProperties(user, userModel));
        return userModel;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Success delete(Long userId) {
        log.debug("Delete user with id = {}", userId);
        userRepository.deleteById(userId);
        return Success.success();
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<UserModel> getAll() {
        log.debug("Get all users");
        List<User> users = userRepository.findAll();
        List<UserModel> userModels = new LinkedList<>();
        users.forEach(u -> {
            UserModel userModel = new UserModel();
            BeanUtils.copyProperties(u, userModel);
            userModels.add(userModel);
        });
        return userModels;
    }
}
