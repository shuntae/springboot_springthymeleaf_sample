package jp.co.introduction.common.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IchibaItemModel {

    private int count;

    private int page;

    private int first;

    private int last;

    private int hits;

    private int carrier;

    private int pageCount;

    @JsonProperty("Items")
    private List<ItemModel> itemList;

}