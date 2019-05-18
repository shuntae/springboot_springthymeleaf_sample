package jp.co.introduction.base.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResModel {

    private boolean success;
    private String message;
    private boolean warn;
    private boolean error;

}