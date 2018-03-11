package com.noname.service.impl;

import com.noname.entity.Student;
import com.noname.mapper.StudentMapper;
import com.noname.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentMapper, Student> implements StudentService {
}
