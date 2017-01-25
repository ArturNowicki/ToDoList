package com.sda.service;

public interface UserSecurityService {

    String validatePasswordResetToken(int id, String token);

}
