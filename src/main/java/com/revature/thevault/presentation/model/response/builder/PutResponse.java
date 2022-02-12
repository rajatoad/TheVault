package com.revature.thevault.presentation.model.response.builder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(builder = PutResponse.Builder.class)
public class PutResponse extends GenericResponse{
    private List<Object> updatedObject;

    public PutResponse(Builder builder){
        super(builder);
        this.updatedObject = builder.updatedObject;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GenericResponse.Builder<Builder>{
        private List<Object> updatedObject;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder updatedObject(List<Object> updatedObject){
            this.updatedObject = updatedObject;
            return this;
        }

        public PutResponse build(){
            return new PutResponse(this);
        }

    }
}
