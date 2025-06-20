package com.project.demo.controller;

import com.project.demo.entity.DeliveryClerk;
import com.project.demo.service.DeliveryClerkService;
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
 *投递员：(DeliveryClerk)表控制层
 *
 */
@RestController
@RequestMapping("/delivery_clerk")
public class DeliveryClerkController extends BaseController<DeliveryClerk,DeliveryClerkService> {

    /**
     *投递员对象
     */
    @Autowired
    public DeliveryClerkController(DeliveryClerkService service) {
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
