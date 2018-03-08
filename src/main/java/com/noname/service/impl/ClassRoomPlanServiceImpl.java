package com.noname.service.impl;

import com.noname.entity.Classroom;
import com.noname.mapper.ClassroomMapper;
import com.noname.service.ClassRoomPlanService;
import org.springframework.stereotype.Service;

@Service
public class ClassRoomPlanServiceImpl extends BaseServiceImpl<ClassroomMapper, Classroom> implements ClassRoomPlanService {
}
