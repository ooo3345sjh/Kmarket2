package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVO {
    private String ordNo;
    private String uid;
    private int ordCount;
    private int ordPrice;
    private int ordDiscount;
    private int ordDelivery;
    private int savePoint;
    private int usedPoint;
    private int ordTotPrice;
    private String recipName;
    private String recipHp;
    private String recipZip;
    private String recipAddr1;
    private String recipAddr2;
    private int ordPayment;
    private int ordComplete;
    private String ordDate;
    private String ordState;

    // 추가 필드
    private String company;
    private String prodName;
    private String thumb1;
    private String cate1;
    private String cate2;
    private String sellerUid;

    public String getThumb1() {
        return cate1 + "/" + cate2 + "/" + thumb1;
    }
}
