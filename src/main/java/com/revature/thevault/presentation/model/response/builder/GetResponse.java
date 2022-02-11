package com.revature.thevault.presentation.model.response.builder;

import com.revature.thevault.repository.entity.AccountEntity;
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
public class GetResponse{
    boolean success;
    String responseType;
    String message;
    AccountEntity gotObject;

    private GetResponse(Builder builder){
        this.success = builder.success;
        this.responseType = builder.responseType.toString();
        this.message = builder.message;
        this.gotObject = builder.gotObject;
    }

    public static class Builder{
        private boolean success;
        private ResponseType responseType;
        private String message;
        private AccountEntity gotObject;

        public Builder(boolean success, ResponseType responseType, String message){
            this.success = success;
            this.responseType = responseType;
            this.message = message;
        }
        public Builder gotObject(AccountEntity gotObject){
            this.gotObject = gotObject;
            return this;
        }

        public GetResponse build(){
            return new GetResponse(this);
        }
    }

}
