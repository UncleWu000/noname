package com.noname.service.impl;

import com.noname.entity.Selected;
import com.noname.mapper.SelectedMapper;
import com.noname.service.BaseService;
import com.noname.service.SelectedService;
import com.noname.vo.CourseStuSelectedVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectedServiceImpl extends BaseServiceImpl<SelectedMapper, Selected> implements SelectedService{
    @Override
    public List<CourseStuSelectedVO> getCourseStuSelected(String courseName) {
        return dao.getCourseStuSelected(courseName);
    }
}
