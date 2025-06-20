package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *取消订单：(CancellationOfOrder)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CancellationOfOrder")
public class CancellationOfOrder implements Serializable {

    //CancellationOfOrder编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cancellation_of_order_id")
    private Integer cancellation_of_order_id;
    // 订单编号
    @Basic
    private String order_number;
    // 衣物名称
    @Basic
    private String clothing_items_;
    // 衣物类型
    @Basic
    private String clothes_type_;
    // 用户账号
    @Basic
    private Integer user_account;
    // 姓名
    @Basic
    private String full_name;
    // 审核状态
    @Basic
    private String audit_status;
    // 取消原因
    @Basic
    private String cancellation_reason;
    // 审核回复
    @Basic
    private String review_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
