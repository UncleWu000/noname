package com.noname.service;

import com.noname.entity.Classroom;
import com.noname.entity.CourseRoomPlan;
import com.noname.vo.PlanVO;

import java.util.List;

public interface CourseRoomPlanService extends BaseService<CourseRoomPlan>{

    /**
     * 查planVO
     * @return
     */
    List<PlanVO> getPlanVo();
}
