package com.noname.mapper;

import com.noname.entity.Timeslot;
import com.noname.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimeslotMapper extends BaseMapper<Timeslot> {

    List<Timeslot> getUsefulTs(@Param("roomId")Integer roomId);
}
