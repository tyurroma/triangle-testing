package model;

import lombok.*;

@Getter
@Builder (toBuilder = true)
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    private String timestamp;
    private String status;
    private String error;
    private String exception;
    private String message;
    private String path;
}
