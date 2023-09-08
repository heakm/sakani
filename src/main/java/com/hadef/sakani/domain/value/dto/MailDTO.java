package com.hadef.sakani.domain.value.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailDTO {

    private String recipient;
    private String title;
    private String text;
}
