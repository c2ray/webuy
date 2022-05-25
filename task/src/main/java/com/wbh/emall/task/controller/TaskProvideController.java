package com.wbh.emall.task.controller;

import com.wbh.emall.common.entity.rpc.PicCheckerDto;
import com.wbh.emall.task.service.TaskProvideServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WBH
 * @since 2022/3/25
 */
@RestController
@RequestMapping("/api/task")
public class TaskProvideController {
    
    private final TaskProvideServiceImpl taskProvideService;
    
    public TaskProvideController(TaskProvideServiceImpl taskProvideService) {
        this.taskProvideService = taskProvideService;
    }
    
    @PostMapping("/img/check")
    public void addImgCheckTask(@RequestBody PicCheckerDto picCheckerDto) {
        taskProvideService.addPicCheckTask(picCheckerDto);
    }
    
    
}
