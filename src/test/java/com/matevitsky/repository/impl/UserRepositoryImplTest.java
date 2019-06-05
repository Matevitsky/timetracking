package com.matevitsky.repository.impl;


import com.matevitsky.entity.Role;
import com.matevitsky.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryImplTest {

    @InjectMocks
    private UserRepositoryImpl userRepository = new UserRepositoryImpl();
    @Mock
    private ResultSet resultset;

    @Test
    public void mapToObject() throws SQLException {
        when(resultset.getInt("ID")).thenReturn(1);
        when(resultset.getString("Name")).thenReturn("user");
        when(resultset.getString("Email")).thenReturn("user@gmail.com");
        when(resultset.getString("Password")).thenReturn("user");
        when(resultset.getString("RoleName")).thenReturn("User");

        User expected = User.newBuilder()
                .withId(1)
                .withEmail("user@gmail.com")
                .withName("user")
                .withPassword("user")
                .withRole(new Role(0, "User"))
                .build();
        User actual = userRepository.mapToObject(resultset);

        assertEquals(expected, actual);

    }
}