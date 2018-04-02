package com.noname.service;

import com.noname.entity.Selected;
import com.noname.vo.CourseStuSelectedVO;

import java.util.List;

public interface SelectedService extends BaseService<Selected>{

    List<CourseStuSelectedVO> getCourseStuSelected(String courseName);
}
