package com.swimtothend.activiti.dao;

import com.swimtothend.activiti.entity.LeaveInfo;

public interface LeaveInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(LeaveInfo record);

    int insertSelective(LeaveInfo record);

    LeaveInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LeaveInfo record);

    int updateByPrimaryKey(LeaveInfo record);
}