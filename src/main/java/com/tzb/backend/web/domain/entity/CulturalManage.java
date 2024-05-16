package com.tzb.backend.web.domain.entity;


import com.tzb.backend.web.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cultural_manage")
public class CulturalManage {

    @Id
    @GeneratedValue
    private Integer id; // 文物ID
    private String name; // 文物名称
    private String description; // 文物描述
    private String image; // 图片/多媒体资料
    private String era; // 年代/时期
    private String material; // 材质

    private double weight; // 重量
    private double width; // 宽度
    private double height; // 高度
    private double depth; // 深度

    private String value; // 文物价值
    private String story; // 文物故事
    @Column(name = "protection_status")
    private String protectionStatus; // 保护状态


    @Convert(converter = StringListConverter.class)
    @Column(name = "exhibition_history")
    private List<String> exhibitionHistory; // 展出历史
    @Convert(converter = StringListConverter.class)
    @Column(name = "related_artifacts")
    private List<String> relatedArtifacts; // 关联文物

}
