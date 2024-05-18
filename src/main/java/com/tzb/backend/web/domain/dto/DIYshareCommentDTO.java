package com.tzb.backend.web.domain.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DIYshareCommentDTO {

    private Long id;
    private String topic;
    private String content;
    private LocalDateTime sendTime = LocalDateTime.now();


    private String nickname;
    private Boolean gender;
    private String avatar;
    private String address;
    private String email;
}
