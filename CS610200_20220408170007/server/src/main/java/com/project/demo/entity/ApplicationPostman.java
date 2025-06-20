package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *申请投递员：(ApplicationPostman)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ApplicationPostman")
public class ApplicationPostman implements Serializable {

    //ApplicationPostman编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_postman_id")
    private Integer application_postman_id;
    // 用户账户
    @Basic
    private Integer user_account;
    // 姓名
    @Basic
    private String full_name;
    // 身份证号
    @Basic
    private String id_number;
    // 联系电话
    @Basic
    private String contact_number;
    // 审核状态
    @Basic
    private String audit_status;
    // 审核回复
    @Basic
    private String review_reply;
    // 申请理由
    @Basic
    private String reasons_for_application;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
