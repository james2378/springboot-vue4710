package com.project.demo.controller;

import com.project.demo.entity.LaundryService;
import com.project.demo.service.LaundryServiceService;
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
 *洗衣服务：(LaundryService)表控制层
 *
 */
@RestController
@RequestMapping("/laundry_service")
public class LaundryServiceController extends BaseController<LaundryService,LaundryServiceService> {

    /**
     *洗衣服务对象
     */
    @Autowired
    public LaundryServiceController(LaundryServiceService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
