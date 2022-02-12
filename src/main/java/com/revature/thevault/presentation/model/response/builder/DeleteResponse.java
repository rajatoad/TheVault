package com.revature.thevault.presentation.model.response.builder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(HttpStatus.OK)
@JsonDeserialize(builder = DeleteResponse.Builder.class)
public class DeleteResponse extends GenericResponse{
    private Object deletedObject;

    public DeleteResponse(Builder builder){
        super(builder);
        this.deletedObject = builder.deletedObject;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GenericResponse.Builder<Builder>{
        private Object deletedObject;

        @Override
        public Builder getThis(){
            return this;
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
