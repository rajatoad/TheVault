package com.revature.thevault.presentation.model.response.builder;

import com.revature.thevault.utility.enums.ResponseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
    boolean success;
    String responseType;
    String message;

    protected GenericResponse(Builder<?> builder){
        this.success = builder.success;
        this.responseType = builder.responseType;
        this.message = builder.message;
    }

    public static Builder builder(){
        return new Builder(){
            @Override
            public Builder getThis(){
                return this;
            }
        };
    }

    public abstract static class Builder<T extends Builder<T>>{
        private boolean success;
        private String responseType;
        private String message;

        public abstract T getThis();

        public T success(boolean success){
            this.success = success;
            return this.getThis();
        }

        public T responseType(ResponseType responseType){
            this.responseType = responseType.toString();
            return this.getThis();
        }

        public T message(String message){
            this.message = message;
            return this.getThis();
        }

        public GenericResponse build(){
            return new GenericResponse(this);
        }

    }
}
