package model;

import lombok.*;

@Getter
@Builder (toBuilder = true)
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TriangleResponse {

    private String id;
    private float firstSide;
    private float secondSide;
    private float thirdSide;
}
