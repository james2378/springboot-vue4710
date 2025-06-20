package com.project.demo.controller;

import com.project.demo.entity.ApplicationPostman;
import com.project.demo.service.ApplicationPostmanService;
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
 *申请投递员：(ApplicationPostman)表控制层
 *
 */
@RestController
@RequestMapping("/application_postman")
public class ApplicationPostmanController extends BaseController<ApplicationPostman,ApplicationPostmanService> {

    /**
     *申请投递员对象
     */
    @Autowired
    public ApplicationPostmanController(ApplicationPostmanService service) {
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
