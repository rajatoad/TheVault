package com.revature.thevault.presentation.model.response.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse {
    boolean success;

    protected GenericResponse(Builder<?> builder){
        this.success = builder.success;
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

        public abstract T getThis();

        public T success(boolean success){
            this.success = success;
            return this.getThis();
        }

        public GenericResponse build(){
            return new GenericResponse(this);
        }

    }
}
