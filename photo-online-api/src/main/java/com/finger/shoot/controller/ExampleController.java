package com.finger.shoot.controller;

import com.finger.portal.base.util.ResponseModel;
import com.finger.shoot.entity.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengmd on 2017/7/18.
 */
@Slf4j
@RestController
@RequestMapping("/analysis")
public class ExampleController {

    /**
     * 查询明细表-日期、地接
     * @param
     * @return
     */
    @RequestMapping(value = "/XXXXXXXXXXXX", method = RequestMethod.POST)
    public Object selectMetaAnalysisByAreaLineDate(@RequestBody Example example){
        ResponseModel susResp = ResponseModel.getSuccessResponseModel();

        return susResp;
    }
}
