package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *衣物分类：(ClothingClassification)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ClothingClassification")
public class ClothingClassification implements Serializable {

    //ClothingClassification编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clothing_classification_id")
    private Integer clothing_classification_id;
    // 衣物类型
    @Basic
    private String clothes_type_;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
