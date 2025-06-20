package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *洗衣订单：(LaundryOrder)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "LaundryOrder")
public class LaundryOrder implements Serializable {

    //LaundryOrder编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laundry_order_id")
    private Integer laundry_order_id;
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
    // 收取地址
    @Basic
    private String receiving_address;
    // 预约时间
    @Basic
    private Timestamp time_of_appointment;
    // 订单状态
    @Basic
    private String order_status;
    // 订单编号
    @Basic
    private String order_number;
    // 注意事项
    @Basic
    private String matters_needing_attention;
    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
