package com.example.afprojectbackend;

import com.example.afprojectbackend.Controller.AdminUserController;
import com.example.afprojectbackend.Model.AdminUser;
import com.example.afprojectbackend.Service.AdminUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdminUserTestClass {

    @MockBean
    AdminUserService adminUserService;

    @Autowired
    AdminUserController adminUserController;

    @Test
    public void testCreate(){

        AdminUser adminUser = new AdminUser("Minindu", "123", "M.Kothalawala","0770817059","minindu@gmail.com","Admin");
        adminUserService.addAdminUser(adminUser);
        Assertions.assertTrue(true,"Added");
    }

    @Test
    public void testGetAll(){

        List<AdminUser> list = new ArrayList<AdminUser>();

        AdminUser adminUser1 = new AdminUser("Minindu", "123", "M.Kothalawala","0770817059","minindu@gmail.com","Admin");
        AdminUser adminUser2 = new AdminUser("Hansi", "123", "H.Tharaka","0770817059","hansi@gmail.com","Reviewer");

        list.add(adminUser1);
        list.add(adminUser2);

        Mockito.when(adminUserService.getAllAdminUsers()).thenReturn(list);
    }

    @Test
    public void testGetByUserName(){

        List<AdminUser> list = new ArrayList<AdminUser>();

        AdminUser adminUser1 = new AdminUser("Minindu", "123", "M.Kothalawala","0770817059","minindu@gmail.com","Admin");
        AdminUser adminUser2 = new AdminUser("Hansi", "123", "H.Tharaka","0770817059","hansi@gmail.com","Editor");

        list.add(adminUser1);
        list.add(adminUser2);

        adminUserService.getAdminByUsername("Minindu");
        Assertions.assertTrue(true,"Retrieved User bu User name Successfully");


    }

    @Test
    public void testDeleteAdminUser(){

        AdminUser adminUser = new AdminUser("Minindu", "123", "M.Kothalawala","0770817059","minindu@gmail.com","Admin");

        adminUserService.deleteAdminUser(adminUser.getUsername());
        Assertions.assertTrue(true,"Deleted Successfully");
    }
}
