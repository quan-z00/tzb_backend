package com.tzb.backend.web.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import com.tzb.backend.common.annotation.ResultWrapper;
import com.tzb.backend.web.domain.entity.DIYshareComment;
import com.tzb.backend.web.service.DIYshareCommentService;
import com.tzb.backend.web.specification.CommentSpecificationDTO;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@ResultWrapper
@RequestMapping("/comment")
public class DIYshareCommentController {

    private final DIYshareCommentService diYshareCommentService;


    @Autowired
    public DIYshareCommentController(DIYshareCommentService diYshareCommentService) {
        this.diYshareCommentService = diYshareCommentService;
    }

    @SaIgnore
    @GetMapping("/findAll") //分页查询
    public Object getAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        return diYshareCommentService.findAll(pageable);
    }


    @GetMapping("/entities/search") //分页查询和搜索功能
    public Object searchEntities(
            CommentSpecificationDTO commentSpecificationDTO,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return diYshareCommentService.searchAndFindAll(commentSpecificationDTO, pageable);
    }

    @GetMapping("/findTopic") //根据评论标题进行查询
    public Object findByTopic(@RequestParam(name = "topic") String topic){
         return diYshareCommentService.findByTopic(topic);
    }

    @PostMapping("/add") //添加评论
    public Object addComment(@RequestBody DIYshareComment comment) {
        return diYshareCommentService.addComment(comment);
    }

    @PostMapping("/del/{id}") //删除评论
    public void delComment(@PathVariable Integer id){
        diYshareCommentService.delComment(id);
    }

    @PutMapping("/put/{id}") //更改评论
    public void updateComment(@PathVariable Integer id, @RequestBody DIYshareComment commentDetails) throws NotFoundException {
        diYshareCommentService.updateComment(id, commentDetails);
    }
}
