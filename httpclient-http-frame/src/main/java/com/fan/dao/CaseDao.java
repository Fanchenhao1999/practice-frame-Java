package com.fan.dao;

import java.util.List;

/**
 * @className: CaseDao
 * @author: ChenHao
 * @date: 2022/11/25
 **/
public interface CaseDao {

    //查询CaseDao表中的所以测试用例
    List<com.fan.entity.Case> CaseAll();

}
