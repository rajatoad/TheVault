package com.revature.thevault.presentation.model.response.builder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(builder = GetResponse.Builder.class)
public class GetResponse extends GenericResponse{

    private List gotObject;

    public GetResponse(Builder builder){
        super(builder);
        this.gotObject = builder.gotObject;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GenericResponse.Builder<Builder>{
        private List gotObject;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder gotObject(List gotObject){
            this.gotObject = gotObject;
            return this;
        }

        public GetResponse build(){
            return new GetResponse(this);
        }
    }

}
