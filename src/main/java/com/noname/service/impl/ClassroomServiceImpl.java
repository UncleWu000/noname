package com.noname.service.impl;

import com.noname.entity.Classroom;
import com.noname.mapper.ClassroomMapper;
import com.noname.service.ClassroomService;
import org.springframework.stereotype.Service;

@Service
public class ClassroomServiceImpl extends BaseServiceImpl<ClassroomMapper, Classroom> implements ClassroomService {
}
