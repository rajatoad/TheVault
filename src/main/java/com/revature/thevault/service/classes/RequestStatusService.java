package com.revature.thevault.service.classes;

import com.revature.thevault.repository.dao.RequestStatusRepository;
import com.revature.thevault.repository.entity.RequestStatusEntity;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("requestStatusService")
public class RequestStatusService {

    @Autowired
    private RequestStatusRepository requestStatusRepository;

    public RequestStatusEntity getRequestStatusByName(String name) {
        try{
            return requestStatusRepository.findByName(name);
        }catch (Exception e){
            throw new InvalidRequestException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
