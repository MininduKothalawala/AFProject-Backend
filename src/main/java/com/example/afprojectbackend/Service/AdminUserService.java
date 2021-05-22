package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.AdminUser;
import com.example.afprojectbackend.Repository.AdminUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;

    public AdminUserService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    public void addAdminUser(AdminUser adminUser){
        adminUserRepository.insert(adminUser);
    }

    public List<AdminUser> getAllAdminUsers(){
        return adminUserRepository.findAll();
    }

    public void deleteAdminUser(String id){
        adminUserRepository.deleteById(id);
    }

    public Optional<AdminUser> getAdminByUsername(String username){
        return adminUserRepository.findById(username);
    }

}
