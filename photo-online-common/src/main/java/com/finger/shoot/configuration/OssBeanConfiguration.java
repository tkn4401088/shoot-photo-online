package com.finger.shoot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by pmd
 */
@Configuration
@ConfigurationProperties(prefix="spring.oss")
public class OssBeanConfiguration {
    public String pic;
    public String jscss;
    public String endpoint;
    public String key;
    public String secret;
    public String url;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getJscss() {
        return jscss;
    }

    public void setJscss(String jscss) {
        this.jscss = jscss;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
