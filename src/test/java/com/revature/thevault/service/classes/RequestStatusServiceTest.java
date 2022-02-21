package com.revature.thevault.service.classes;

import com.revature.thevault.repository.dao.RequestStatusRepository;
import com.revature.thevault.repository.entity.RequestStatusEntity;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RequestStatusServiceTest {
    @InjectMocks

    @Autowired
    RequestStatusService rss;
    int userID;
    String userLogin;
    String userPass;
    RequestStatusEntity rse;
    @MockBean
   RequestStatusRepository rsp;
    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userID = 1;
        userLogin = "James";
        userPass = "Cameron";
    rse = new RequestStatusEntity(userID,userLogin);
        Mockito.when(rss.getRequestStatusByName(userLogin)).thenReturn(rse);
    }
    @Test
    public void testRequestStatusEntityByName(){
        Assertions.assertThrows(InvalidRequestException.class, () ->{
            rss.getRequestStatusByName(userLogin);
        });
    }

}