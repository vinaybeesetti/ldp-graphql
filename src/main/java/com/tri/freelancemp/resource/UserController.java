package com.tri.freelancemp.resource;

import com.tri.freelancemp.entity.User;
import com.tri.freelancemp.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public User getUserById(@Argument Integer id) {
       return this.userService.fetchUserById(id);
    }

    @MutationMapping
    public User createUser(@Argument User user) {
        return this.userService.createUser(user);
    }
}
