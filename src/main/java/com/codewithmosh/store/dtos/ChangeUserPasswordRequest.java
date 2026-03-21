package com.codewithmosh.store.dtos;

import lombok.Data;

@Data
public class ChangeUserPasswordRequest {
    private String oldPassword;
    private String newPassword;

}
