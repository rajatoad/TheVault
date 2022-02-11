package com.revature.thevault.presentation.model.response.builder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PutResponse extends GenericResponse{
    private Object updatedObject;

    public PutResponse(Builder builder){
        super(builder);
        this.updatedObject = builder.updatedObject;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GenericResponse.Builder<Builder>{
        private Object updatedObject;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder updatedObject(Object updatedObject){
            this.updatedObject = updatedObject;
            return this;
        }

        public PutResponse build(){
            return new PutResponse(this);
        }

    }
}
