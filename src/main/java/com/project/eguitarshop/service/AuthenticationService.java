package com.project.eguitarshop.service;

import com.project.eguitarshop.model.User;

public interface AuthenticationService {
    User getCurrentUser();
    String getCurrentUserId();
    User signUpUser(String username, String password, String repeatedPassword);
}
