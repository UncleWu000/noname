package com.noname.service.impl;

import com.noname.entity.Coursegroup;
import com.noname.mapper.CourseGroupMapper;
import com.noname.service.ClassgroupService;
import org.springframework.stereotype.Service;

@Service
public class ClassgroupServiceImpl extends BaseServiceImpl<CourseGroupMapper, Coursegroup> implements ClassgroupService {
}
