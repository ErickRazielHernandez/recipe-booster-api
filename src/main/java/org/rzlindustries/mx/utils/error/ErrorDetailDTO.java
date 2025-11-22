package org.rzlindustries.mx.utils.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDetailDTO {
    private String code;
    private String message;
    private String path;
}
