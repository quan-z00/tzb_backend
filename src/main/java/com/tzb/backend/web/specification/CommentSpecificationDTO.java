package com.tzb.backend.web.specification;


import lombok.Data;

@Data
public class CommentSpecificationDTO {


    private String topic;
    private String nickname;


    public boolean isEmpty() {
        return (topic == null || topic.isEmpty()) &&
                (nickname == null || nickname.isEmpty());
    }
}
