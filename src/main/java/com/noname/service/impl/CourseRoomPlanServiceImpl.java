package com.noname.service.impl;

import com.noname.entity.CourseRoomPlan;
import com.noname.mapper.CourseRoomPlanMapper;
import com.noname.service.CourseRoomPlanService;
import com.noname.vo.PlanVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRoomPlanServiceImpl extends BaseServiceImpl<CourseRoomPlanMapper, CourseRoomPlan> implements CourseRoomPlanService {


    public List<PlanVO> getPlanVo() {
        return dao.getAllPlanVo();
    }
}
