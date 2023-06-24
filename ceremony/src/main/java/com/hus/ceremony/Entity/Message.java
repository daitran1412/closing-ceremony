package com.hus.ceremony.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.*;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int messageId;

    @Column(name = "name", nullable = true, columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "message", nullable = false, columnDefinition = "varchar(5000)")
    private String message;

    @Column(name = "social_link", nullable = true, columnDefinition = "varchar(1000)")
    private String socialLink;

    @Column(name = "date_time", nullable = false)
    private String dateTime;

}
