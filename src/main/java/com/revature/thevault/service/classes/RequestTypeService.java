package com.revature.thevault.service.classes;

import com.revature.thevault.repository.dao.RequestTypeRepository;
import com.revature.thevault.repository.entity.RequestTypeEntity;
import com.revature.thevault.service.exceptions.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("requestTypeService")
public class RequestTypeService {

    @Autowired
    private RequestTypeRepository requestTypeRepository;

    public RequestTypeEntity getRequestTypeByName(String requestType) {
        RequestTypeEntity requestTypeEntity = requestTypeRepository.findByName(requestType);
        if(requestTypeEntity != null) return requestTypeEntity;
        else throw new InvalidRequestException(HttpStatus.BAD_REQUEST, "Request Type not found: " + requestType);
    }
}
