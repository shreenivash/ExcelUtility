package org.excelUtility.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublishAppSizeRequest {
    private String appStamp;
    private String deviceName;
    private String platformName;
    private String deviceVersion;
    private double tfSize;
    private double ondeviceSize;

}
