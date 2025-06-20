package com.project.demo.controller;

import com.project.demo.entity.CancellationOfOrder;
import com.project.demo.service.CancellationOfOrderService;
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
 *取消订单：(CancellationOfOrder)表控制层
 *
 */
@RestController
@RequestMapping("/cancellation_of_order")
public class CancellationOfOrderController extends BaseController<CancellationOfOrder,CancellationOfOrderService> {

    /**
     *取消订单对象
     */
    @Autowired
    public CancellationOfOrderController(CancellationOfOrderService service) {
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
