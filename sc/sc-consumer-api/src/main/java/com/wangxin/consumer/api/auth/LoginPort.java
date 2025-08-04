package com.wangxin.consumer.api.auth;

public interface LoginPort {
    void login(LoginRequest request) throws AuthenticationException;
    void logout();
}