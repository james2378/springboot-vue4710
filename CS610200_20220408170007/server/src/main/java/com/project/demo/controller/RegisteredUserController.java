package com.project.demo.controller;

import com.project.demo.entity.RegisteredUser;
import com.project.demo.service.RegisteredUserService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *注册用户：(RegisteredUser)表控制层
 *
 */
@RestController
@RequestMapping("/registered_user")
public class RegisteredUserController extends BaseController<RegisteredUser,RegisteredUserService> {

    /**
     *注册用户对象
     */
    @Autowired
    public RegisteredUserController(RegisteredUserService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapid_number = new HashMap<>();
        mapid_number.put("id_number",String.valueOf(paramMap.get("id_number")));
        List listid_number = service.select(mapid_number, new HashMap<>()).getResultList();
        if (listid_number.size()>0){
            return error(30000, "字段身份证号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
