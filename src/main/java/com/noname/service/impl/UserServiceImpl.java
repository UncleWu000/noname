package com.noname.service.impl;

import com.noname.entity.User;
import com.noname.mapper.UserMapper;
import com.noname.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService{

    public String userImport(List<ArrayList<String>> userExcel) {
        String res = "";
        Integer cntRow = 0;
        for(ArrayList<String> row : userExcel){
            cntRow++;
            Integer cnt = 0;
            User user = new User();
            for(String cell : row){

                if(cnt == 0 ){
                    user.setNickname(cell);
                }else if(cnt == 1){
                    user.setEmail(cell);
                }else if(cnt == 2){
                    user.setPswd(cell);
                }else if(cnt == 3){
                    user.setStatus(Long.valueOf(cell));
                }
                cnt ++;
            }

            user.setCreateTime(new Date());
            if(dao.insertSelective(user)==0){
                res += res.equals("")?"":", ";
                res += cntRow;
            }
        }
        if(res.equals("")){
            res = "全部导入成功！";
        }else{
            res = "第"+res+"条记录导入失败，请检查数据的完整性或尝试重新导入";
        }

        return res;
    }
}
