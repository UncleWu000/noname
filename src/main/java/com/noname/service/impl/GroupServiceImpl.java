package com.noname.service.impl;

import com.noname.entity.StudentGroup;
import com.noname.mapper.GroupMapper;
import com.noname.service.GroupService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl extends BaseServiceImpl<GroupMapper, StudentGroup> implements GroupService {
}
