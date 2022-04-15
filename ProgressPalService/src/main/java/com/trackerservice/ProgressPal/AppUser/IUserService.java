package com.trackerservice.ProgressPal.AppUser;

import java.util.List;

public interface IUserService {
    User saveUser(User user);
    UserRole saveRole(UserRole role);
    void addRoleToUser(String email, String roleName);
    User getUser(String email);
    List<User> getUsers();
}
