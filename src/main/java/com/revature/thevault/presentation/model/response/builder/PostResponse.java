package com.revature.thevault.presentation.model.response.builder;

import com.revature.thevault.utility.enums.ResponseType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ResponseStatus(HttpStatus.CREATED)
public class PostResponse extends GenericResponse {
    private Object createdObject;

    public PostResponse(Builder builder) {
        this.success = builder.success;
        this.responseType = builder.responseType.toString();
        this.message = builder.message;
        this.createdObject = builder.createdObject;
    }

    public static class Builder {
        private boolean success;
        private ResponseType responseType;
        private String message;
        private Object createdObject;

        public Builder(boolean success, ResponseType responseType, String message) {
            this.success = success;
            this.responseType = responseType;
            this.message = message;
        }

        public Builder createdObject(Object createdObject) {
            this.createdObject = createdObject;
            return this;
        }

        public PostResponse build() {
            return new PostResponse(this);
        }
    }
}

