$(function(){

    // �먮ℓ�� �뺣낫 �앹뾽 �꾩슦湲�
    $('.latest .info .company > a').click(function(e){
        e.preventDefault();
        $('#popSeller').addClass('on');
    });

    // 臾몄쓽�섍린 �앹뾽 �꾩슦湲�
    $('.btnQuestion').click(function(e){
        e.preventDefault();
        $('.popup').removeClass('on');
        $('#popQuestion').addClass('on');
    });

    // 二쇰Ц�곸꽭 �앹뾽 �꾩슦湲�
    $('.latest .info .orderNo > a').click(function(e){
        e.preventDefault();
        $('#popOrder').addClass('on');
    });

    // �섏랬�뺤씤 �앹뾽 �꾩슦湲�
    $('.latest .confirm > .receive').click(function(e){
        e.preventDefault();
        $('#popReceive').addClass('on');
    });

    // �곹뭹�� �묒꽦 �앹뾽 �꾩슦湲�
    $('.latest .confirm > .review').click(function(e){
        e.preventDefault();
        $('#popReview').addClass('on');
    });

    // �앹뾽 �リ린
    $('.btnClose').click(function(){
        $(this).closest('.popup').removeClass('on');
    });

    // �곹뭹�� �묒꽦 �덉씠�낅컮 湲곕뒫
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