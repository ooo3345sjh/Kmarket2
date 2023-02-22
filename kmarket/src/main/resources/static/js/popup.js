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
    $('.latest .info .company > a').click(function(e){
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
        const titleVal = title.text(); // 제목
        const contentVal = content.text(); // 내용
        console.log(title.children().eq(0).val());

        let jsonData = {
            "type":type,
            "email":email.text(),
            "title":titleVal,
            "content":contentVal
        }
        console.log(jsonData);

        /*
        ajaxAPI("my/home/sendEmail"+sellerUid, null, "post").then((response) => {

            if (response == null || response.sellerInfo == null)
            alert('Request fail...');

            else {


            }

        }).catch((errorMsg) => {
            console.log(errorMsg)
        });*/

    });

    // 주문상세 팝업 띄우기
    $('.latest .info .orderNo > a').click(function(e){
        e.preventDefault();
        $('#popOrder').addClass('on');
    });

    // 수취확인 팝업 띄우기
    $('.latest .confirm > .receive').click(function(e){
        e.preventDefault();
        $('#popReceive').addClass('on');
    });

    // 상품평 작성 팝업 띄우기
    $('.latest .confirm > .review').click(function(e){
        e.preventDefault();
        $('#popReview').addClass('on');
    });

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
            alert('rated ' + currentRating);
            console.log('DOM element ', $el);
        }
    });

});