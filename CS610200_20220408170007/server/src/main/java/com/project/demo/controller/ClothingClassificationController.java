package com.project.demo.controller;

import com.project.demo.entity.ClothingClassification;
import com.project.demo.service.ClothingClassificationService;
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
 *衣物分类：(ClothingClassification)表控制层
 *
 */
@RestController
@RequestMapping("/clothing_classification")
public class ClothingClassificationController extends BaseController<ClothingClassification,ClothingClassificationService> {

    /**
     *衣物分类对象
     */
    @Autowired
    public ClothingClassificationController(ClothingClassificationService service) {
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
