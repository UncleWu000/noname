package com.noname.mapper;

import com.noname.entity.CourseRoomPlan;
import com.noname.util.BaseMapper;
import com.noname.vo.PlanVO;

import java.util.List;

public interface CourseRoomPlanMapper extends BaseMapper<CourseRoomPlan> {

    List<PlanVO> getAllPlanVo();
}