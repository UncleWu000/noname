package com.noname.service;

import com.noname.entity.Classroom;
import com.noname.vo.ClassroomVO;

import java.util.List;

public interface ClassroomService extends BaseService<Classroom> {

    List<ClassroomVO> getClassroomVO();
}
