package com.revature.thevault.presentation.model.response.builder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ResponseStatus(HttpStatus.OK)
@JsonDeserialize(builder = GetResponse.Builder.class)
public class GetResponse extends GenericResponse{

    private Object gotObject;

    public GetResponse(Builder builder){
        super(builder);
        this.gotObject = builder.gotObject;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GenericResponse.Builder<Builder>{
        private Object gotObject;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder gotObject(Object gotObject){
            this.gotObject = gotObject;
            return this;
        }

        public GetResponse build(){
            return new GetResponse(this);
        }
    }

}
