package org.rzlindustries.mx.utils.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ErrorResponseDTO {
    private String message;
    private int status;
    private String path;
    private List<ErrorDetailDTO> details;
}
