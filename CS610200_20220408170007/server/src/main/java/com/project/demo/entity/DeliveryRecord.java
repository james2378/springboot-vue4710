package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *投递记录：(DeliveryRecord)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DeliveryRecord")
public class DeliveryRecord implements Serializable {

    //DeliveryRecord编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_record_id")
    private Integer delivery_record_id;
    // 订单编号
    @Basic
    private String order_number;
    // 衣物名称
    @Basic
    private String clothing_items_;
    // 衣物类型
    @Basic
    private String clothes_type_;
    // 服务价格
    @Basic
    private Integer service_price;
    // 用户账号
    @Basic
    private Integer user_account;
    // 姓名
    @Basic
    private String full_name;
    // 联系电话
    @Basic
    private String contact_number;
    // 投递费用
    @Basic
    private String delivery_fee;
    // 合计费用
    @Basic
    private String total_cost;
    // 收取地址
    @Basic
    private String receiving_address;
    // 投递员
    @Basic
    private Integer delivery_clerk;
    // 派送状态
    @Basic
    private String dispatch_status;
    // 投递时间
    @Basic
    private Timestamp delivery_time;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
