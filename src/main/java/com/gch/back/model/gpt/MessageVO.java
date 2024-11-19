package com.gch.back.model.gpt;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MessageVO {
    private String role;
    private String content;
}
