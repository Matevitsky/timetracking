package com.matevitsky.entity.dto;

/**
 * Class used for admin activity request page
 */
public class UserForActivityRequest {

    private final Integer id;
    private final String name;


    public UserForActivityRequest(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
