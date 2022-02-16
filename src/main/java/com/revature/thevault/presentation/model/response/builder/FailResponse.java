package com.revature.thevault.presentation.model.response.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

//EXPERIMENTAL DO NOT USE IT

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FailResponse extends GenericResponse{
    private List exception;

    public FailResponse(Builder builder){
        super(builder);
        this.exception = builder.exception;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GenericResponse.Builder<Builder>{
        private List exception;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder exception(Exception exception){
            this.exception = Collections.singletonList(exception);
            return this;
        }

        public FailResponse build(){
            return new FailResponse(this);
        }

    }

}
