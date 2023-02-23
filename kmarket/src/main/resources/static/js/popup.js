$(function(){
    // 판매자 정보 입력 태그
    let seller_level = $('#popSeller table tr').eq(0).children().eq(1)
    let seller_company = $('#popSeller table tr').eq(1).children().eq(1)
    let seller_ceo = $('#popSeller table tr').eq(2).children().eq(1)
    let seller_tel = $('#popSeller table tr').eq(3).children().eq(1)
    let seller_fax = $('#popSeller table tr').eq(4).children().eq(1)
    let seller_email = $('#popSeller table tr').eq(5).children().eq(1)
    let seller_bizRegNum = $('#popSeller table tr').eq(6).children().eq(1)
    let seller_addr = $('#popSeller table tr').eq(7).children().eq(1)

    // 판재자의 등급(숫자)을 한글로 변환하는 함수
    function convertSellerLevel(level){
        switch (level){
            case 1: return level="일반"; break;
            case 2: return level="고객만족우수"; break;
            case 3: return level="파워딜러"; break;
        }
    }

    // 판매자 정보 팝업 띄우기
    $('.info .company > a').click(function(e){
        e.preventDefault();

        // 판매자 정보 가져오는 AJAX
        const sellerUid = $(this).data("selleruid");
        console.log(sellerUid);

        let jsonData = {
            "uid" : sellerUid
        }

        ajaxAPI("my/home/sellerInfo/"+sellerUid, null, "get").then((response) => {

            if (response == null || response.sellerInfo == null)
                alert('Request fail...');

            else {
                const sellerInfo = response.sellerInfo;

                seller_level.text(convertSellerLevel(sellerInfo.level));
                seller_company.text(sellerInfo.company);
                seller_ceo.text(sellerInfo.ceo);
                seller_tel.text(sellerInfo.tel);
                seller_fax.text(sellerInfo.fax);
                seller_email.text(sellerInfo.email);
                seller_bizRegNum.text(sellerInfo.bizRegNum);
                seller_addr.text(sellerInfo.addr);

                $('#popSeller').addClass('on');
            }

        }).catch((errorMsg) => {
            console.log(errorMsg)
        });

    });

    const email = $('#popQuestion table tr').eq(1).children().eq(1);
    const title = $('#popQuestion table tr').eq(2).children().eq(1).children().eq(0);
    const content = $('#popQuestion table tr').eq(3).children().eq(1).children().eq(0);


    // 문의하기 팝업 띄우기
    $('.btnQuestion').click(function(e){
        e.preventDefault();

        // 판매자정보로부터 가져온 회사의 이메일 주소 입력
        email.text(seller_email.text());

        $('.popup').removeClass('on');
        $('#popQuestion').addClass('on');
    });

    // 문의하기에서 등록하기 버튼 클릭
    $('#popQuestion .btnPositive').click(function(e){
        e.preventDefault();

        const type = $('input[name=kind]:checked', '#popQuestion').val(); // 문의유형

        console.log(type);
        if(type === undefined) {
            alert("문의유형을 선택해주세요."); 
            return;
        } 
        if(title.val() == "") {
            alert("제목을 입력해주세요.");
            return;
        }
        if(content.val() == ""){
            alert("내용을 입력해주세요.");
            return;
        }

        let jsonData = {
            "email": email.text(),
            "title": "["+ type + "]" + title.val(),
            "content":content.val()
        }

        $(".btnClose").trigger("click");
        alert("문의 이메일을 전송했습니다.");
        ajaxAPI("my/home/sendEmail", jsonData, "post").then((response) => {

            if (response == null || response.status == 0)
                alert('Request fail...');

            else {

            }

        }).catch((errorMsg) => {
            console.log(errorMsg)
        });

    });

    // 주문상세 팝업 띄우기
    $('.info .orderNo > a').click(function(e){
        e.preventDefault();

        const ordNo = $(this).text();
        prodNo = $(".latest table").find('tr:eq(1)').data("prodno");
        const queryString = "?ordNo="+ordNo+"&prodNo="+prodNo;


        ajaxAPI("my/home/detailOrder"+queryString, null, "get").then((response) => {

            if (response == null || response.orderLog == null)
                alert('Request fail...');

            else {
                const orderLog = response.orderLog;
                $("#popOrder .order .date").text(orderLog.ordDate.substr(0, 10));
                $("#popOrder .order .ordNo").text(orderLog.ordNo);
                $("#popOrder .order .company").text(orderLog.company);
                $("#popOrder .order .prodName").text(orderLog.prodName);
                $("#popOrder .order .prodCount").text(orderLog.orderItemVO.count);
                $("#popOrder .order .prodPrice").text(orderLog.orderItemVO.price.toLocaleString('ko-KR') + "원");
                $("#popOrder .order .price").children(":eq(1)").text(orderLog.orderItemVO.price.toLocaleString('ko-KR') + "원");
                $("#popOrder .order .discount").children(":eq(1)").text(
                        (orderLog.orderItemVO.discountPrice>0?
                            "-"+orderLog.orderItemVO.discountPrice.toLocaleString('ko-KR'):orderLog.orderItemVO.discountPrice.toLocaleString('ko-KR')) + "원"
                );
                $("#popOrder .order .delivery").children(":eq(1)").text(orderLog.orderItemVO.delivery.toLocaleString('ko-KR') + "원");
                $("#popOrder .order .total").children(":eq(1)").text(orderLog.orderItemVO.total.toLocaleString('ko-KR') + "원");
                $("#popOrder .order .status").text(orderLog.ordState);
                $("#popOrder .delivery .name").text(orderLog.recipName);
                $("#popOrder .delivery .hp").text(orderLog.recipHp);
                $("#popOrder .delivery .address").text(orderLog.addr);
                $('#popOrder').addClass('on');
            }

        }).catch((errorMsg) => {
            console.log(errorMsg)
        });

    });

    // 수취확인 팝업 띄우기
    let ordNo;
    $('.confirm > .receive').click(function(e){
        e.preventDefault();
        ordNo = $(".ordNo", $(this).closest("tr")).text();
        $('#popReceive').addClass('on');
    });

    // 수취확인 버튼 클릭
    $("#popReceive .btnConfirm").click(function(e){
        e.preventDefault();

        let jsonData = {"ordNo": ordNo}
         ajaxAPI("my/ordered/receive", jsonData, "post").then((response) => {

            if (response == null || response.result == 0)
                alert('Request fail...');

            else {
                 alert("구매확정 되었습니다.")
                 $(".btnClose").trigger("click");
            }

        }).catch((errorMsg) => {
            console.log(errorMsg)
        });

    })

    // 상품평 작성 팝업 띄우기
    let prodNo;
    $('.confirm > .review').click(function(e){
        e.preventDefault();
        prodNo = $(this).closest("table").find('tr:eq(1)').data("prodno");
        const prodName = $(this).closest("tr").find('li.prodName a').text();

        $("#popReview .productName").text(prodName);
        $('#popReview').addClass('on');
    });

    let rating = 0;
    $('#popReview .btnPositive').click(function (e){
        e.preventDefault();
        let review = $(this).closest("form").find("textarea[name=review]").val();

        if(rating == 0){
            alert("만족도를 선택해주세요.");
            return;
        }
        if(review == ""){
            alert("내용을 선택해주세요.");
            return;
        }
        let jsonData = {
            "rating":rating,
            "content":review,
            "prodNo":prodNo
        }
        ajaxAPI("my/home/review/write", jsonData, "post").then((response) => {

            if (response == null || response.result == 0)
                alert('Request fail...');

            else {
                alert("리뷰등록에 성공했습니다.")
                $(".btnClose").trigger("click");
            }

        }).catch((errorMsg) => {
            console.log(errorMsg)
        });


    })
    // 팝업 닫기
    $('.btnClose').click(function(e){
        e.preventDefault();
        $(this).closest('.popup').removeClass('on');
    });

    // 상품평 작성 레이팅바 기능
    $(".my-rating").starRating({
        starSize: 20,
        useFullStars: true,
        strokeWidth: 0,
        useGradient: false,
        minRating: 1,
        ratedColors: ['#ffa400', '#ffa400', '#ffa400', '#ffa400', '#ffa400'],
        callback: function(currentRating, $el){
            // alert('rated ' + currentRating);
            rating = currentRating;
            console.log('DOM element ', $el);
        }
    });

});