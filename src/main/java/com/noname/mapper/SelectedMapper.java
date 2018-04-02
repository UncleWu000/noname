package com.noname.mapper;

import com.noname.entity.Selected;
import com.noname.util.BaseMapper;
import com.noname.vo.CourseStuSelectedVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectedMapper extends BaseMapper<Selected>{

    List<CourseStuSelectedVO> getCourseStuSelected(@Param("cname") String courseName);
}