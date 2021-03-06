package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.AdminUser;
import com.example.afprojectbackend.Repository.AdminUserRepository;
import com.example.afprojectbackend.Service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://af-icaf-frontend.azurewebsites.net")
@RequestMapping("/api/adminuser")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Autowired
    private AdminUserRepository adminUserRepository;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping("/addadminuser")
    public ResponseEntity<?> addAdminUser(@RequestBody AdminUser adminUser){
        System.out.println("adduser"+adminUser.getName());
        adminUserService.addAdminUser(adminUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/alladmin")
    public ResponseEntity<List<AdminUser>> getAllAdminUsers(){
        return ResponseEntity.ok(adminUserService.getAllAdminUsers());
    }

    @GetMapping("/getadminuser/{username}")
    public Object getAdminUser(@PathVariable String username){
        System.out.println("admin name :"+username);
        return ResponseEntity.ok(adminUserService.getAdminByUsername(username));
    }

    @DeleteMapping("/deleteuser/{id}")
    public void deleteAdminUser(@PathVariable String id){
        adminUserService.deleteAdminUser(id);
    }
}
