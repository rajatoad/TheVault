package com.revature.thevault.presentation.model.response;

import com.revature.thevault.utility.enums.ResponseType;
import com.revature.thevault.utility.validation.ResponseValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
    boolean success;
    String requestType;
    String message;

    private GenericResponse(GenericResponseBuilder builder){
        this.success = builder.success;
        this.requestType = builder.requestType;
        this.message = builder.message;
    }

    public static class GenericResponseBuilder{
        final boolean success;
        String requestType;
        String message;

        public GenericResponseBuilder(boolean success){
            this.success = success;
        }

        public GenericResponseBuilder requestType(ResponseType responseType){
            this.requestType = responseType.getRequestType();
            return this;
        }

        public GenericResponseBuilder message(String message){
            this.message = message;
            return this;
        }

        public GenericResponse build(){
            GenericResponse response = new GenericResponse(this);
            validateGenericResponse(response);
            return response;
        }

        private void validateGenericResponse(GenericResponse response){
            ResponseValidation.validateGenericResponse(response);
        }

    }

}
