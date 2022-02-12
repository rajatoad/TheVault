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
@ResponseStatus(HttpStatus.CREATED)
@JsonDeserialize(builder = PostResponse.Builder.class)
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
