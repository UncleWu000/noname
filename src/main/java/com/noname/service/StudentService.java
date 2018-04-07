package com.noname.service;

import com.noname.entity.Student;
import org.apache.commons.lang3.mutable.MutableInt;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService extends BaseService<Student> {


    boolean studentInport(MultipartFile file);

}
