 <script>
	$(function () {
		let contextRoot = '${request.getContextPath()}';
		console.log(contextRoot);
		// 상품 수량 - 클릭시
		$('.decrease').click(function () {
			let numTag = $(this).next();
			let num = numTag.val();
			if(num == 1) {
				return;
			} else {
				numTag.val(--num);
				let total = (num * ${product.discountPrice}).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
				$('.total > span').text(total);
			}
		})

		// 상품 수량 + 클릭시
		$('.increase').click(function () {
			let numTag = $(this).prev();
			let num = numTag.val();
			//console.log(num);
			if(num >= ${product.stock}) {
				alert('재고량이 부족합니다.');
				return;
			} else {
				numTag.val(++num);
				let total = (num * ${product.discountPrice}).toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
				$('.total > span').text(total);
			}
		})

		// 해당 상품 리뷰를 불러오는 AJAX
		let pageGroupEnd;
		$(document).ready(function () {
			let jsonData = {"cate1":'${product.cate1}', "cate2":'${product.cate2}', "prodNo":'${product.prodNo}'};

	        $.post(contextRoot + '/product/review', jsonData, function(data){
	        	pageGroupEnd = data.pageGroupEnd;
	        	let arr = JSON.parse(data.reviews); // JSON 문자열을 배열로 변환
	        	if(arr.length == 0){
                	$('.review > ul').append('<li><p>등록된 리뷰가 없습니다.</p></li>');
	        	}
				for(let review of arr){
					let liTag = '<li>'
	                          + '<div>'
	                          + '<h5 class="rating star' + review.rating + '">상품평</h5>'
	                          + '<span>' + review.uid.substring(0,3) + '****** ' + review.rdate.substring(0, 10) + '</span>'
	                          + '</div>'
	                          + '<h3>${vo.prodName}</h3>'
			                  + '<p>' + review.content + '</p>'
                			  + '</li>';

                	$('.review > ul').append(liTag);
                	$('.paging').html(data.pageTag);
				}
	        });
		})

		// 상품리뷰 페이지 클릭시 해당 상품리뷰가 나오도록 하는 AJAX
		$(document).on('click', '.paging > a',function (e) {
			e.preventDefault();
			let pg = $(this).text();
			//console.log(pg.includes('다음'));
			if(pg.includes('다음')){
				pg = pageGroupEnd + 1;
				console.log(pg);
			} else if(pg.includes('이전')){
				pg = pageGroupEnd - 1;
				console.log(pg);
			}
			let jsonData = {"cate1":'${product.cate1}', "cate2":'${product.cate2}', "prodNo":'${product.prodNo}', "pg":pg};

	        $.post(contextRoot + '/product/review', jsonData, function(data){
	        	//console.log(data.pageTag);
	        //	console.log(data.reviews);
	        	$('.review > ul').children().remove(); // 현재 페이지에 출력된 상품리뷰 삭제
	        	let arr = JSON.parse(data.reviews); // JSON 문자열을 배열로 변환
				for(let review of arr){
					console.log(review.revNo);
					console.log(review.prodNo);
					console.log(review.uid);
					console.log(review.content);

					let liTag = '<li>'
	                          + '<div>'
	                          + '<h5 class="rating star' + review.rating + '">상품평</h5>'
	                          + '<span>' + review.uid.substring(0,3) + '****** ' + review.rdate.substring(0, 10) + '</span>'
	                          + '</div>'
	                          + '<h3>${vo.prodName}</h3>'
			                  + '<p>' + review.content + '</p>'
                			  + '</li>';


                	$('.review > ul').append(liTag);
                	$('.paging').html(data.pageTag);
				}
	        });
		})

		// 장바구니에 해당 상품을 추가하는 이벤트 함수
		$(document).on('click', '.cart', function () {

			let num = $('input[name=num]').val();
			let total = $('.total > span').text();
			let jsonData = {
					"prodNo":'${product.prodNo}',
					"uid":'${sessMember.uid}',
					"thumb1":'${product.thumb1}',
					"prodName":'${product.prodName}',
					"descript":'${product.descript}',
					"cate1":'${product.cate1}',
					"cate2":'${product.cate2}',
					"count": num,
					"price":'${product.price}',
					"discount":'${product.discount}',
					"point":'${product.point}',
					"delivery":'${product.delivery}',
					"total": total.replaceAll(',', '')
			};

	        $.post(contextRoot + '/product/view.do', jsonData, function(data){
	        	console.log(data.result);
	        	if(data.result != 0){
	        		$('#cartMove').show();
	        		$('#background').show();
	        		$('body').css("overflow", "hidden");
	        	};
	        });
		})

		$(document).on('click', '#cartMove > button', function () {
			let value = $(this).text();

			if(value == '계속 쇼핑'){
	        	$('#cartMove').hide();
	        	$('#background').hide();
	        	$('body').css("overflow", "auto");
			} else{
        		location.href = contextRoot + '/product/cart.do';
			}
		})

		// 구매하기 버튼 클릭시 바로 해당 상품을 주문하는 페이지로 이동하는 이벤트
		$('.order').click(function () {
			let num = $('input[name=num]').val(); // 주문 갯수
			let total = $('.total > span').text(); // 총 상품금액
			let list=[];

			// 천 단위 ',' 처리
			let price = String('${product.price}').replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
			let point = String('${product.point}').replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
			let delivery = String('${product.delivery}').replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
			list.push({
					"prodNo":'${product.prodNo}',
					"cate1":'${product.cate1}',
					"cate2":'${product.cate2}',
					"count": num,
					"price": price,
					"discount":'${product.discount}%',
					"point": point,
					"delivery": delivery,
					"thumb1":'${product.thumb1}',
					"prodName":'${product.prodName}',
					"descript":'${product.descript}'
			});

			sessionStorage.setItem("orderList", JSON.stringify(list)); // 세션 저장

			location.href = contextRoot + '/product/order';
		})
	})
</script>