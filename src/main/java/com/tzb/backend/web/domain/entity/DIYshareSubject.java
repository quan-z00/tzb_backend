package com.tzb.backend.web.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tzb.backend.admin.domain.entity.Profile;
import com.tzb.backend.admin.domain.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "DIYshare_subject")
public class DIYshareSubject {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //帖子标题
    private String content; //帖子内容
    private LocalDateTime Uptime = LocalDateTime.now(); //发帖时间


    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonIgnore
    private Profile userProfile;
}
