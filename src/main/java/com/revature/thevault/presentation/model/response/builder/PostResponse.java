package com.revature.thevault.presentation.model.response.builder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse extends GenericResponse{
    private Object createdObject;

    public PostResponse(Builder builder){
        super(builder);
        this.createdObject = builder.createdObject;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GenericResponse.Builder<Builder>{
        private Object createdObject;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder createdObject(Object createdObject){
            this.createdObject = createdObject;
            return this;
        }

        public PostResponse build(){
            return new PostResponse(this);
        }
    }
}
