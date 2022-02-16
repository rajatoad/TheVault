package com.revature.thevault.presentation.model.response.builder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(HttpStatus.OK)
@JsonDeserialize(builder = PutResponse.Builder.class)
public class PutResponse extends GenericResponse{
    private List updatedObject;

    public PutResponse(Builder builder){
        super(builder);
        this.updatedObject = builder.updatedObject;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GenericResponse.Builder<Builder>{
        private List updatedObject;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder updatedObject(List updatedObject){
            this.updatedObject = updatedObject;
            return this;
        }

        public PutResponse build(){
            return new PutResponse(this);
        }

    }
}
