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
@JsonDeserialize(builder = DeleteResponse.Builder.class)
public class DeleteResponse extends GenericResponse{
    private List deletedObject;

    public DeleteResponse(Builder builder){
        super(builder);
        this.deletedObject = builder.deletedObject;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GenericResponse.Builder<Builder>{
        private List deletedObject;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder deletedObject(List deletedObject){
            this.deletedObject = deletedObject;
            return this;
        }

        public DeleteResponse build(){
            return new DeleteResponse(this);
        }
    }
}
