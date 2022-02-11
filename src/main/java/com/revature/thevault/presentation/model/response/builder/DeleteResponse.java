package com.revature.thevault.presentation.model.response.builder;

import com.revature.thevault.utility.enums.ResponseType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(HttpStatus.OK)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteResponse{
    boolean success;
    String responseType;
    String message;
    Object deletedObject;

    public DeleteResponse(Builder builder){
        this.success = builder.success;
        this.responseType = builder.responseType.toString();
        this.message = builder.message;
        this.deletedObject = builder.deletedObject;
    }

    public static class Builder{
        private boolean success;
        private ResponseType responseType;
        private String message;
        private Object deletedObject;

        public Builder(boolean success, ResponseType responseType, String message){
            this.success = success;
            this.responseType = responseType;
            this.message = message;
        }
        public Builder deletedObject(Object deletedObject){
            this.deletedObject = deletedObject;
            return this;
        }

        public DeleteResponse build(){
            return new DeleteResponse(this);
        }
    }
}
