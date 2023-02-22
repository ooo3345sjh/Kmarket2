function typesChange(e){
 var user = ["가입","탈퇴","회원정보","로그인"];
 var coupon = ["쿠폰/할인혜택","포인트","제휴","이벤트"];
 var order = ["상품","결제","구매내역","영수증/증빙"];
 var delivery = ["배송상태/기간","배송정보확인/변경","해외배송","당일배송","해외직구"];
 var cancel = ["반품신청/철회","반품정보확인/변경","교환 AS신청/철회","교환정보확인/변경","취소신청/철회","취소확인/환불정보"];
 var travel = ["여행/숙박","항공"];
 var safeDeal = ["서비스 이용규칙 위반","지식재산권침해","법령 및 정책위반 상품","게시물 정책위반","직거래/외부거래유도","표시광고","청소년 위해상품/이미지"];
 var target = document.getElementById("type");

 if(e.value == "user") var types = user
 else if(e.value == "coupon") var types = coupon
 else if(e.value == "order") var types = order
 else if(e.value == "delivery") var types = delivery
 else if(e.value == "cancel") var types = cancel
 else if(e.value == "travel") var types = travel
 else if(e.value == "safeDeal") var types = safeDeal;

target.options.length = 0;

 for(x in types){
    var opt = document.createElement("option");
    opt.value = types[x];
    opt.innerHTML = types[x];
    target.appendChild(opt);
 }
}