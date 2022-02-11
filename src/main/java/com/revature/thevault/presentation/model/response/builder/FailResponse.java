package com.revature.thevault.presentation.model.response.builder;

import com.revature.thevault.utility.enums.ResponseType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailResponse extends GenericResponse {
    private String exception;

    public FailResponse(Builder builder){
        this.success = builder.success;
        this.responseType = builder.responseType.toString();
        this.message = builder.message;
        this.exception = builder.exception;
    }

    public static class Builder{
        private boolean success;
        private ResponseType responseType;
        private String message;
        private String exception;

        public Builder(boolean success, ResponseType responseType, String message){
            this.success = success;
            this.responseType = responseType;
            this.message = message;
        }
        public Builder exception(Exception exception){
            this.exception = exception.toString();
            return this;
        }

        public FailResponse build(){
            return new FailResponse(this);
        }
    }
}
