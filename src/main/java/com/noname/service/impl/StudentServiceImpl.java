package com.noname.service.impl;

import com.noname.entity.Student;
import com.noname.mapper.StudentMapper;
import com.noname.service.StudentService;
import com.noname.util.ExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public boolean studentInport(MultipartFile file) {

        List<ArrayList<String>> excel = ExcelUtil.excelRead(file);

        try {
            for (ArrayList<String> row : excel){

                int index = 0;
                Student student = new Student();
                for(String cell : row){
                    if(index == 0)
                        student.setNo(cell);
                    else if(index == 1)
                        student.setPwd(cell);
                    else if(index == 2){
                        student.setSex(cell);
                    }else if(index == 3)
                        student.setName(cell);
                    else if (index == 4)
                        student.setClas(cell);
                    index ++;
                }

                dao.insertSelective(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
