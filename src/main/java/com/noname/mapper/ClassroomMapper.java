package com.noname.mapper;

import com.noname.entity.Classroom;
import com.noname.util.BaseMapper;
import com.noname.vo.ClassroomVO;

import java.util.List;

public interface ClassroomMapper extends BaseMapper<Classroom> {

    List<ClassroomVO> getClassroomVO();
}