package com.matevitsky.service.impl;

import com.matevitsky.entity.User;
import com.matevitsky.exception.ErrorException;
import com.matevitsky.repository.interfaces.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;
    private User user = User.newBuilder().build();

    @Test
    public void createUser() throws ErrorException {
        when(userService.createUser(user)).thenReturn(true);
        assertTrue(userService.createUser(user));
    }

    @Test
    public void deleteUser() {
        when(userService.deleteUser(user.getId())).thenReturn(true);
        assertTrue(userService.deleteUser(user.getId()));
    }

    @Test
    public void updateUser() {
        when(userService.updateUser(user)).thenReturn(user);
        assertEquals(user, userService.updateUser(user));
    }

    @Test
    public void getUser() {
        Optional<User> user = Optional.of(User.newBuilder().withId(1).build());
        when(userService.getUserById(1)).thenReturn(user);
        User actual = userService.getUserById(1).get();
        User expected = User.newBuilder().withId(1).build();
        assertEquals(expected, actual);
    }

    @Test
    public void findUserByEmail() throws ErrorException {
        User user = User.newBuilder().withEmail("user@gmail.com").build();
        when(userService.findUserByEmail("user@gmail.com")).thenReturn(Optional.ofNullable(user));
        User expected = User.newBuilder().withEmail("user@gmail.com").build();
        assertEquals(expected, userService.findUserByEmail("user@gmail.com").get());
    }
}