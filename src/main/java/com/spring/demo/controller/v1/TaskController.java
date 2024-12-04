package com.spring.demo.controller.v1;

import com.spring.demo.service.TaskProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskProducer taskProducer;

    public TaskController(TaskProducer taskProducer) {
        this.taskProducer = taskProducer;
    }

    @PostMapping
    public ResponseEntity<String> produceTask(@RequestBody String task) {
        taskProducer.sendTask(task);
        return ResponseEntity.ok("Task sent");
    }
}
