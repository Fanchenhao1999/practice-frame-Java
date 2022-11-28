package com.fan.request;

/**
 * @className: requestInterface
 * @author: ChenHao
 * @date: 2022/11/28
 **/
public class RequestInterfaceUrl {
    //登录 授权
    String login_url = "/AuthService/login";
    //退件扫描
    String checkin_url = "/ReturnOrderService/checkin";
    //添加秃无头件
    String claimAdd_url = "/ReturnOrderService/claim/add";
    //查询无头件
    String claimQuery_url = "/ReturnOrderService/claim/query";
    //删除无头件
    String claimDelete_url = "/ReturnOrderService/claim/delete";
    //无头件客户认领
    String claimMatchClear_url = "/ReturnOrderService/claim/match/add";

    public String getLogin_url() {
        return login_url;
    }

    public void setLogin_url(String login_url) {
        this.login_url = login_url;
    }

    public String getCheckin_url() {
        return checkin_url;
    }

    public void setCheckin_url(String checkin_url) {
        this.checkin_url = checkin_url;
    }

    public String getClaimAdd_url() {
        return claimAdd_url;
    }

    public void setClaimAdd_url(String claimAdd_url) {
        this.claimAdd_url = claimAdd_url;
    }

    public String getClaimQuery_url() {
        return claimQuery_url;
    }

    public void setClaimQuery_url(String claimQuery_url) {
        this.claimQuery_url = claimQuery_url;
    }

    public String getClaimDelete_url() {
        return claimDelete_url;
    }

    public void setClaimDelete_url(String claimDelete_url) {
        this.claimDelete_url = claimDelete_url;
    }

    public String getClaimMatchClear_url() {
        return claimMatchClear_url;
    }

    public void setClaimMatchClear_url(String claimMatchClear_url) {
        this.claimMatchClear_url = claimMatchClear_url;
    }


}
