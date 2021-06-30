package com.example.afprojectbackend;

import com.example.afprojectbackend.Model.AdminUser;
import com.example.afprojectbackend.Repository.AdminUserRepository;
import com.example.afprojectbackend.Service.AdminUserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.PrivateKey;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class AfProjectBackendApplicationTests {

    @Autowired
    private AdminUserService service;

    @MockBean
    private AdminUserRepository repository;

    @Test
    public void getUserTest(){
        when(repository.findAll()).thenReturn(Stream.of(new AdminUser("testadmin","testpwd","testname","0710148281","test@gmail.com","admin")).collect(Collectors.toList()));

        assertEquals(1,service.getAllAdminUsers().size());
    }

    @Test
    public void getUserByname(){

     String name = "saman";
        when(repository.findByUsername(name)).thenReturn(java.util.Optional.of(new AdminUser("", "", "", "", "", "")));
        assertEquals(true,service.getAdminByUsername(name).isEmpty());

    }


    @Test
    public void saveUserTest(){
        AdminUser adminUser = new AdminUser("1","1","1","1","1","1");

        when(repository.save(adminUser)).thenReturn(adminUser);

//        assertEquals(adminUser, service.addAdminUser(adminUser));

    }

    @Test
    public void deleteUsertest(){
        AdminUser adminUser = new AdminUser("1","1","1","1","1","1");
//        service.deleteAdminUser(adminUser);
//        verify(repository,times(1).delete(adminUser));
    }


}
