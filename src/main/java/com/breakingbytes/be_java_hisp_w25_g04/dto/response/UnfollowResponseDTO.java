package com.breakingbytes.be_java_hisp_w25_g04.dto.response;

import lombok.*;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UnfollowResponseDTO {
    private String message;
}
