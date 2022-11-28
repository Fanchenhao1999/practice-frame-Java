package com.fan.entity;

import java.util.Date;

/**
 * @className: Csae
 * @author: ChenHao
 * @date: 2022/11/25
 **/
public class Case {
   private Integer CASE_ID;
   private String CASE_NAME;
   private String REQUEST_URL;
   private String CRATE_USER_NAME;
   private Date CRATE_DATA;
   private String MEMO;

    public Integer getCASE_ID() {
        return CASE_ID;
    }

    public void setCASE_ID(Integer CASE_ID) {
        this.CASE_ID = CASE_ID;
    }

    public String getCASE_NAME() {
        return CASE_NAME;
    }

    public void setCASE_NAME(String CASE_NAME) {
        this.CASE_NAME = CASE_NAME;
    }

    public String getREQUEST_URL() {
        return REQUEST_URL;
    }

    public void setREQUEST_URL(String REQUEST_URL) {
        this.REQUEST_URL = REQUEST_URL;
    }

    public String getCRATE_USER_NAME() {
        return CRATE_USER_NAME;
    }

    public void setCRATE_USER_NAME(String CRATE_USER_NAME) {
        this.CRATE_USER_NAME = CRATE_USER_NAME;
    }

    public Date getCRATE_DATA() {
        return CRATE_DATA;
    }

    public void setCRATE_DATA(Date CRATE_DATA) {
        this.CRATE_DATA = CRATE_DATA;
    }

    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }

    @Override
    public String toString() {
        return "Case{" +
                "CASE_ID=" + CASE_ID +
                ", CASE_NAME='" + CASE_NAME + '\'' +
                ", REQUEST_URL='" + REQUEST_URL + '\'' +
                ", CRATE_USER_NAME=" + CRATE_USER_NAME +
                ", CRATE_DATA='" + CRATE_DATA + '\'' +
                ", MEMO='" + MEMO + '\'' +
                '}';
    }
}
