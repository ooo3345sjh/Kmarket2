package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointVO {
    private int pointNo;
    private String uid;
    private String ordNo;
    private int point;
    private String pointDate;
}
