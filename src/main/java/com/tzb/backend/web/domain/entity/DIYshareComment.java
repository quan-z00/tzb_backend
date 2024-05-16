package com.tzb.backend.web.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tzb.backend.admin.domain.entity.Profile;
import com.tzb.backend.admin.domain.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "DIYshare_comment")
public class DIYshareComment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topic; //评论标题
    private String content; //评论内容
    private LocalDateTime sendTime = LocalDateTime.now(); //发送评论的时间


    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonIgnore
    private Profile userProfile;

}
