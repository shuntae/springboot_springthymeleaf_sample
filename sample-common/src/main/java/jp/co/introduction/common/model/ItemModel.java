package jp.co.introduction.common.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel {

    private String itemCode;

    private String itemName;

    private String shopName;

    private String shopCode;

    private String genreId;

    private String catchcopy;

    private int itemPrice;

    private String itemCaption;

    private String itemUrl;

    private String shopUrl;

    private List<UrlModel> smallImageUrls;

    private List<UrlModel> mediumImageUrls;

    private String affiliateUrl;

    private String shopAffiliateUrl;

    private boolean imageFlag;

    private boolean availability;

    private boolean taxFlag;

    private boolean postageFlag;

    private boolean creditCardFlag;

    private boolean shopOfTheYearFlag;

    private boolean shipOverseasFlag;

    private String shipOverseasArea;

    private boolean asurakuFlag;

    private String asurakuClosingTime;

    private String asurakuArea;

    private float affiliateRate;

    private String startTime;

    private String endTime;

    private Integer reviewCount;

    private Double reviewAverage;

    private int pointRate;

    private String pointRateStartTime;

    private String pointRateEndTime;

    private boolean giftFlag;

    private List<String> tagIds;

}