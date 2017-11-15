package com.finger.shoot.common;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by zb on 2017/6/7.
 */
@Component
public class RestTemplateUtils {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Http GET请求Url里带参数的请求
     * @param url             请求URL
     * @param respType        返回值类型Class
     * @param pathVariables   参数
     * @param <T>             返回类型
     * @return
     */
    public <T> T httpGetPathVariable(String url, Class<T> respType, Object... pathVariables){
        return restTemplate.getForObject(url, respType, pathVariables);
    }


    /**
     * Http GET请求Url里不带参数的请求
     * @param url             请求URL
     * @param respType        返回类型Class
     * @param urlVariableMap  参数
     * @param <T>             返回类型
     * @return
     */
    public <T> T httpGetUrlVariable(String url, Class<T> respType, Map<String, Object> urlVariableMap){
        String urlParams = "";

        if(urlVariableMap!=null && urlVariableMap.size()>0){

            for(String m : urlVariableMap.keySet()){
                if(!StringUtils.isEmpty(urlParams)) urlParams += "&";
                urlParams += m + "={" + m + "}";
            }
        }

        return restTemplate.getForObject(url + "?" + urlParams, respType, urlVariableMap);
    }

    /**
     * Http Post请求   MediaType  application/json
     * @param url       请求类型
     * @param respType  返回类型Class
     * @param obj       请求对象信息
     * @param <T>       返回类型
     * @return
     */
    public <T> T httpPostMediaTypeJson(String url, Class<T> respType, Object obj){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(obj), headers);
        return restTemplate.postForObject(url, formEntity, respType);
    }


    /**
     * Http Post请求   MediaType  application/x-www-form-urlencoded
     * @param url       请求类型
     * @param respType  返回类型Class
     * @param obj       请求对象信息
     * @param <T>       返回类型
     * @return
     */
    public <T> T httpPostMediaTypeFromData(String url, Class<T> respType, Map<String, ?> obj){
        LinkedMultiValueMap multMap = new LinkedMultiValueMap();
        if(obj!=null && obj.size()>0){
            for(String m : obj.keySet()){
                multMap.add(m, obj.get(m));
            }
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<LinkedMultiValueMap> req = new HttpEntity<LinkedMultiValueMap>(multMap, headers);

        return restTemplate.postForObject(url, req ,respType);
    }
}
