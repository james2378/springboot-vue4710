package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *洗衣服务：(LaundryService)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "LaundryService")
public class LaundryService implements Serializable {

    //LaundryService编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laundry_service_id")
    private Integer laundry_service_id;
    // 衣物名称
    @Basic
    private String clothing_items_;
    // 衣物类型
    @Basic
    private String clothes_type_;
    // 衣物图
    @Basic
    private String clothing_map;
    // 服务价格
    @Basic
    private Integer service_price;
    // 注意事项
    @Basic
    private String matters_needing_attention;
    // 服务说明
    @Basic
    private String service_description;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
