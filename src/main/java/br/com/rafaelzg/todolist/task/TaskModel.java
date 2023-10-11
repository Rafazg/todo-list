package br.com.rafaelzg.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity(name="tb_tasks")
public class TaskModel {
    



    /*
     * ID USER
     *  User
     * Description
     * title
     * initial data
     * end data
     * priority
     * 
     */

     @Id
     @GeneratedValue(generator = "UUID")
     private UUID id;
     private String description;
     private String title;

     @Column(length = 50)
     private LocalDateTime startAt;
     private LocalDateTime endAt;
     private String priority;

     private UUID idUser;

     @CreationTimestamp
     private LocalDateTime createdAt;


}
