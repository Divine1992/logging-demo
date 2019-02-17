package com.divine.logging.dao.dto;

import lombok.Data;

@Data
public class Success {
    private Boolean success;

    private Success(Boolean success) {
        this.success = success;
    }

    public static Success success(){
        return new Success(true);
    }
}
