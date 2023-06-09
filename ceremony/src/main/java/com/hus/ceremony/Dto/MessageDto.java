package com.hus.ceremony.Dto;

@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageDto {

    private int messageId;
    
    private String name;

    private String message;

    private String socialLink;

    private String dateTime;
    
}
