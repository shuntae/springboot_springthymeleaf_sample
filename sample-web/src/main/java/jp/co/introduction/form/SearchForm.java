package jp.co.introduction.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SearchForm implements Serializable {

    @NotNull
    private String keyword;

}
