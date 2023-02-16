// aside 하위항목 숨기기 및 보이기 기능
$(function() {
    $(document).on('click', '#gnb > li', function(){
        $('#gnb > li').not($(this)).children('ol').slideUp(200);
        let display = $(this).children('ol').css('display');
        if(display == 'block'){
            $(this).children('ol').slideUp(200);
        }else{
            $(this).children('ol').slideDown(200);
        }
    });
})

    // 검색어 하이라이트 이벤트
    const searchField = document.querySelector('select[name=searchField]'); // 검색 필드
    const searchWord = document.querySelector('input[name=searchWord]');   // 검색 단어

    $(function (){
        if(searchField.options[searchField.selectedIndex].value === "prodName"){
            $('.prodName').highlight(searchWord.value);
        }
        else if(searchField.options[searchField.selectedIndex].value === "prodNo"){
            $('.prodNo').highlight(searchWord.value);
        }
        else if(searchField.options[searchField.selectedIndex].value === "seller"){
            $('.seller').highlight(searchWord.value);
        }
    })

// 선택삭제 눌려서 삭제 처리
function checkDelete() {
    let url = '/kmarket/admin/product/deleteSelectedProduct';
    let valueArr = new Array();
    let list = $("input[name='check']");

    for (let i=0; i<list.length; i++) {
        if (list[i].checked){ // 선택되어 있으면 배열에 값을 저장
            valueArr.push(list[i].value);
        }
    }
    if (valueArr.length == 0) {
        alert('선택된 상품이 없습니다.');
    } else {
        let chk = confirm('정말 삭제하시겠습니까?');
        console.log(valueArr);
        $.ajax({
            url: url,
            type: 'POST',
            traditional: true,
            data: {valueArr: valueArr},
            dataType: 'json',
            success: function(data) {
                if (data.result = 1) {
                    alert('삭제되었습니다.');
                    location.replace("/kmarket/admin/product/list");
                } else {
                    alert('삭제에 실패했습니다.');
                }
            }
        });
    }
}
////////////////////////////////////////////////////////////////
$(function() {
    // 상품 삭제
    $(document).on('click', '.remove', function(e) {
        e.preventDefault();
        console.log('delete');
        let isDeleteOK = confirm('정말 삭제하시겠습니까?');

        if(isDeleteOK) {
            let prod = $(this).closest('tr');
            let prodNo = $(this).attr('data-no');
            console.log(prodNo);

            $.ajax({
                url: '/kmarket/admin/product/deleteProduct',
                type: 'get',
                data: {'prodNo': prodNo},
                dataType: 'json',
                success: function(data) {
                    console.log(data);
                    if(data.result > 0) {
                        alert('상품이 삭제되었습니다.');
                        prod.hide();
                    }
                }
            });
        }
    });
////////////////////////////////////////////////////////////////
    /** 상품 수정 **/
	$(document).on('click', '.modify', function(e){

		e.preventDefault();
		let tr  = $(this).parent().parent();
		let td  = tr.children();
		let txt = $(this).text();

		let prodName = td.eq(3);
		let price 	 = td.eq(4);
		let discount = td.eq(5);
		let point 	 = td.eq(6);
		let stock 	 = td.eq(7);

		let content1 = prodName.text();
		let content2 = price.text();
		let content3 = discount.text();
		let content4 = point.text();
		let content5 = stock.text();


		if(txt == '[수정]'){
			// 수정모드
			$(this).text('[수정완료]');
			prodName.html('<textarea style="resize:none; width:300px; height:80px;">' + content1 + '</textarea>');
			price.html('<textarea style="resize:none; width:100px; height:20px;">' + content2 + '</textarea>');
			discount.html('<textarea style="resize:none; width:50px; height:20px;">' + content3 + '</textarea>');
			point.html('<textarea style="resize:none; width:50px; height:20px;">' + content4 + '</textarea>');
			stock.html('<textarea style="resize:none; width:50px; height:20px;">' + content5 + '</textarea>');
			prodName.focus();
		} else {
			$(this).text('[수정]');

			let prodNo = $(this).attr('data-no');
			content1 = prodName.children(0).val();
			content2 = price.children(0).val();
			content3 = discount.children(0).val();
			content4 = point.children(0).val();
			content5 = stock.children(0).val();

			let jsonData = {
					"prodNo": prodNo,
					"prodName": content1,
					"price": content2,
					"discount": content3,
					"point": content4,
					"stock": content5
			}

            console.log(jsonData);

            // AJAX 전송
            ajaxAPI('admin/product/modifyProduct', jsonData, "post").then((response) => {
                console.log(response);

                if (response == null)
                    alert('Request fail...');
                else if (response.result == 1) {
                    alert('상품 수정이 완료되었습니다.');
                }

            }).catch((errorMsg) => {
                console.log(errorMsg)
            });

            /*
			$.ajax({
				url: '/kmarket/admin/product/modifyProduct',
				type: 'GET',
				data: JSON.stringify(jsonData),
				dataType: 'json',
				success: function(data){
				    console.log(data);
					if(data.result > 0){
						alert('상품 정보가 수정되었습니다.');
					}
				}
			});
            */
			prodName.text(content1);
			price.text(content2);
			discount.text(content3);
			point.text(content4);
			stock.text(content5);
		}
	});
});
////////////////////////////////////////////////////////////////
// select box 동적 추가 (admin register)
function optionChange(){

	// category2에 속하는 것들 배열로 추가
	let brand = ['브랜드 여성의류', '브랜드 남성의류', '브랜드 진/캐쥬얼', '브랜드 신발/가방', '브랜드 쥬얼리/시계', '브랜드 아웃도어'];
	let cloth = ['여성의류', '남성의류', '언더웨어', '신발', '가방/잡화', '쥬얼리/시계', '화장품/향수', '바디/헤어'];
	let kids = ['출산/육아', '장난감/완구', '유아동 의류', '유아동 신발/잡화'];
	let daily = ['신선식품', '가공식품', '건강식품', '커피/음료', '생필품', '바디/헤어'];
	let hobby = ['가구/DIY', '침구/커튼', '조명/인테리어', '생활용품', '주방용품', '문구/사무용품', '사무기기', '악기/취미', '반려동물용품'];
	let elect = ['노트북/PC', '모니터/프린터', 'PC주변기기', '모바일/태블릿', '카메라', '게임', '영상가전', '주방가전', '계절가전', '생활/미용가전', '음향가전', '건강가전'];
	let sports = ['스포츠의류/운동화', '휘트니스/수영', '구기/라켓', '골프', '자전거/보드/기타레저', '캠핑/낚시', '등산/아웃도어', '건강/의료용품', '건강식품', '렌탈서비스'];
	let car = ['자동차용품', '공구/안전/산업용품'];
	let ticket = ['여행/항공권', '도서/음반/e교육', '공연티켓', 'e쿠폰', '상품권'];

	// category1의 select option에서 value값 받아오기
	// 여기서 value값 = DB에 들어갈 cate1의 고유번호
	let cate1 = $('.category1').val();

	let change;

	switch (cate1) {
		case '10':
			change = brand;
			break;
		case '11':
			change = cloth;
			break;
		case '12':
			change = kids;
			break;
		case '13':
			change = daily;
			break;
		case '14':
			change = hobby;
			break;
		case '15':
			change = elect;
			break;
		case '16':
			change = sports;
			break;
		case '17':
			change = car;
			break;
		case '18':
			change = ticket;
			break;
	}

	// 옵션을 추가하기 전에 select box를 비워준다.
	$('.category2').empty();
	let option;
	option = $("<option value='' disabled selected>2차 분류 선택</option>");
	$('.category2').append(option);

	for (let i=0; i < change.length; i++){
		option = $("<option value="+[i+10]+">"+change[i]+"</option>");
		$('.category2').append(option);
	}
}

// 포인트 1% 자동 입력
function points(){
    let val = $("#price").val() / 100;
    let point = Math.floor(val); // 소수점 버리기
    $("#point").val(point);
}

// 할인율 0~100 사이 값만 등록가능
$(document).on("keyup", "input[name=discount]", function() {
    var val= $(this).val();

    if(val < 0 || val > 100) {
        alert("0~100 범위로 입력해 주십시오.");
        $(this).val('');
    }
});

// 이미지 파일 업로드시 크기, 용량 체크
    function fileCheck(input)
    {
        //console.log("input :", input);
        let obj = input.value;
        // 선택파일의 경로를 분리하여 확장자 구하기
        pathPoint = obj.lastIndexOf('.');
        filePoint = obj.substring(pathPoint + 1, input.length);
        fileType = filePoint.toLowerCase();

        // console.log("fileType :", fileType);

        // 확장자가 이미지 파일이면 사이즈를 체크
        if(fileType=='jpg' || fileType=='gif' || fileType=='png' || fileType=='jpeg' || fileType=='bmp')
        {
            //console.log("size : ", input.files[0].size);
            //console.log("input.files : ", input.files);
            if (input.files && input.files[0].size > (10 * 1024 * 1024)) {
                    alert("파일 사이즈가 10mb 를 넘습니다.\n다시 업로드하여 주십시오.");
                    input.value = "";
                }
        }
        else
        {
            // 업로드 파일이 이미지 확장자가 아닐 경우 경고
            alert('이미지 파일만 업로드 하실수 있습니다.\n다시 업로드하여 주십시오.');
            input.value = "";
            return false;
        }

        // 이미지 확장자이지만 BMP 확장자라면 일단 경고를 준다. (용량이 크기때문)
        if(fileType=='bmp')
        {
            upload = confirm('BMP 파일은 웹상에서 사용하기엔 적절한 이미지 포맷이 아닙니다. \n 그래도 계속 하시겠습니까?');
            if(!upload) return false;
        }
    }


