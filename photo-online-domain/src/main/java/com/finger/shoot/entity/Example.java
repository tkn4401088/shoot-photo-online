package com.finger.shoot.entity;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Example {
	private String shootDate;			//日期
	private BigDecimal salesAmountDay;	//当日总金额
	private BigDecimal salesAmount;		//总金额
	private BigDecimal appSalesDay;		//当日销售金额
	private BigDecimal appSales;		//销售金额

}
