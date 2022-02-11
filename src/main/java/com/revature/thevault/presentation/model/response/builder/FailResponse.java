package com.revature.thevault.presentation.model.response.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FailResponse extends GenericResponse{
    private String exception;

    public FailResponse(Builder builder){
        super(builder);
        this.exception = builder.exception;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder extends GenericResponse.Builder<Builder>{
        private String exception;

        @Override
        public Builder getThis(){
            return this;
        }

        public Builder exception(Exception exception){
            this.exception = exception.toString();
            return this;
        }

        public FailResponse build(){
            return new FailResponse(this);
        }

    }

}
