package com.example.demo2.controller.upload;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Rainmean.Li
 * @create 2019-06-14 17:34
 **/

@Component
@ConfigurationProperties("upload")
public class UploadProperties {

    // 获取存放位置
    private Map<String, String> location;

    // 单个文件大小
    private String maxFileSize;

    // 单次上传总文件大小
    private String maxRequestSize;

    public Map<String, String> getLocation() {
        return location;
    }

    public void setLocation(Map<String, String> location) {
        this.location = location;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getMaxRequestSize() {
        return maxRequestSize;
    }

    public void setMaxRequestSize(String maxRequestSize) {
        this.maxRequestSize = maxRequestSize;
    }

    public String getBasePath() {
        String location = "";
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")) {
            location = this.getLocation().get("windows");
        } else {
            location = this.getLocation().get("linux");
        }
        return location;
    }
}
