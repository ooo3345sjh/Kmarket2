/**
 *  회원가입 JS
 */

window.onload = function() {

	/* 변수 선언 Start */

	// Validation Check
	let uidOk = false;
	let passOk = false;
	let confirmPassOk = false;
	let nameOk = false;
	let emailOk = false;
	let emailAuthOk = false;
	let hpOk = false;

	// Regex
	let reId = /^[a-z0-9_-]{5,20}$/;
	let rePass = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;
	let reName = /^[가-힣]+$/;
	let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	let reEmailCode = /^[0-9]+$/;
	let reHp = /^01(?:0|1|[6-9])-(?:\d{4})-\d{4}$/;

	// 이메일 인증 코드
	let receiveEmailCode = null;

	// Input
	const inputUid = document.querySelector('input[name=km_uid]');
	const inputPass = document.querySelector('input[name=km_pass]');
	const inputConfirmPass = document.querySelector('input[name=km_confirmPass]');
	const inputName = document.querySelector('input[name=name]');
	const inputEmail = document.querySelector('input[name=email]');
	const inputEmailCode = document.querySelector('input[name=emailAuthCode]');
	const inputHp = document.querySelector('input[name=hp]');
	const inputList = [inputUid, inputPass, inputConfirmPass, inputName, inputEmail, inputEmailCode , inputHp];

	// Button
	const btnEmailAuth = document.getElementById('btnEmailAuth');		// 이메일 인증번호 받기
	const btnEmailConfirm = document.getElementById('btnEmailConfirm'); // 이메일 인증코드 확인
	const btnSearchAddr = document.getElementById('btnSearchAddr'); // 이메일 인증코드 확인

	// Validation Result 
	const resultUid = document.querySelector('.msgId');
	const resultPass = document.querySelector('.resultPass');
	const resultConfirmPass = document.querySelector('.resultConfirmPass');
	const resultName = document.querySelector('.resultName');
	const resultNick = document.querySelector('.resultNick');
	const resultEmail = document.querySelector('.resultEmail');
	const resultEmailCode = document.querySelector('.resultEmailCode');
	const resultHp = document.querySelector('.resultHp');

	/* 변수 선언 End */

	// 아이디 입력
	inputUid.addEventListener('focusout', function() {
		let uid = this.value;

		if (uid == "") {
			resultUid.innerText = "필수 정보입니다.";
			resultUid.style.color = "red";
			return;
		}

		if (!reId.test(uid)) {
			resultUid.innerText = "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
			resultUid.style.color = "red";
			return;
		}

        const url = "/register/" + uid + "/check";

		// AJAX 전송
		ajaxAPI(url, jsonData, "get").then((response) => {

			if (response == null)
				alert('Request fail...');

			else if (response.result == 1) {
				resultUid.innerText = "이미 사용중인 아이디입니다.";
				resultUid.style.color = "red";
			}
			
			else {
				resultUid.innerText = "사용가능한 아이디입니다.";
				resultUid.style.color = "green";
				uidOk = true;
			}

		}).catch((errorMsg) => {
			console.log(errorMsg)
		});

	});


	// 패스워드 입력
	inputPass.addEventListener('focusout', function() {
		let pass = this.value;	// 비밀번호
		let confirmPass = inputConfirmPass.value; // 비밀번호 확인

		if (pass == "")
			resultPass.innerText = "필수 정보입니다.";

		else if (!rePass.test(pass))
			resultPass.innerText = "8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.";

		else {
			resultPass.innerText = "";
			passOk = true;
			
			if(pass == confirmPass) {
				confirmPassOk = true;
				resultConfirmPass.innerText = "";
			}
		}

	});

	// 패스워드 확인 입력
	inputConfirmPass.addEventListener('focusout', function() {
		let pass = inputPass.value;		// 비밀번호
		let confirmPass = this.value; // 비밀번호 확인

		if (confirmPass == "")
			resultConfirmPass.innerText = "필수 정보입니다.";

		else if (confirmPass != pass)
			resultConfirmPass.innerText = "비밀번호가 일치하지 않습니다.";

		else {
			resultConfirmPass.innerText = "";
			confirmPassOk = true;
		}
	});

	// 이름 입력
	inputName.addEventListener('focusout', function() {
		let name = this.value;

		if (name == "")
			resultName.innerText = "필수 정보입니다.";

		else if (!reName.test(name))
			resultName.innerText = "한글을 사용하세요. (영문, 특수기호, 공백 사용 불가)";

		else {
			resultName.innerText = "";
			nameOk = true;
		}

	});

	// 이메일 입력
	inputEmail.addEventListener('focusout', function() {

		let email = this.value;

		if(emailOk){}
		
		else if (email == "")
			resultEmail.innerText = "필수 정보입니다.";

		else if (!reEmail.test(email))
			resultEmail.innerText = "이메일 주소를 다시 확인해주세요.";
		
		else {
			resultEmail.innerText = "";
		}
	});

	// 이메일 인증코드 전송
	btnEmailAuth.addEventListener('click', function() {
		let email = inputEmail.value;

		if (emailOk == true) {
			alert('이미 이메일 전송을 완료했습니다.');
			return;
		}

		if (!reEmail.test(email)) {
			resultEmail.innerText = "형식에 맞지 않는 이메일 입니다.";
			return;
		}

		emailOk = true;
		inputEmail.readOnly = true;
		resultEmail.innerText = "이메일 전송 중...";
		resultEmail.style.color = "green";

		let jsonData = {
			"email": email
		};

		ajaxAPI("user/checkEmail", jsonData, "post").then((response) => {
			console.log(response);

			if (response == null) {
				alert('Request fail...');
				inputEmail.readOnly = false;
				emailOk = false;
			}

			else if (response.result != 0) {
				resultEmail.innerText = "이미 사용중인 이메일입니다.";
				resultEmail.style.color = "red";
				inputEmail.readOnly = false;
				emailOk = false;
			}

			else if (response.status == 0) {
				resultEmail.innerText = "이메일 전송에 실패 했습니다. 다시 시도 해주세요.";
				resultEmail.style.color = "red";
				inputEmail.readOnly = false;
				emailOk = false;
			}

			else {
				receiveEmailCode = response.code;
				resultEmail.innerText = "인증코드를 전송했습니다. 이메일을 확인해주세요.";
				resultEmail.style.color = "green";
				
				inputEmail.readOnly = true;
				
				inputEmailCode.style.backgroundColor = "#f7f7f7";
				inputEmailCode.readOnly = false;
			}

		}).catch((errorMsg) => {
			console.log(errorMsg)
		});
	})

	// 이메일 인증 코드 입력
	inputEmailCode.addEventListener('keyup', function() {
		let emailCode = this.value;

		if (!reEmailCode.test(emailCode)) {
			resultEmailCode.innerText = "숫자만 입력해주세요.";
			this.value = emailCode.replace(/[^0-9]/g, ""); // 숫자이외 문자 제외 
		}
	});
	
	inputEmailCode.addEventListener('focusout', function() {
		let emailCode = this.value;
		
		if(emailCode == ""){
			resultEmailCode.innerText = "필수 항목입니다.";
		} 
		
		else if(emailAuthOk != true){
			resultEmailCode.innerText = "인증이 되지 않았습니다.";
		}
		
	});

	// 이메일 인증 코드 확인
	btnEmailConfirm.addEventListener('click', function() {
		let emilCode = inputEmailCode.value;

		if (emailAuthOk == true) {
			alert('이미 인증완료되었습니다.');
		}

		else if (emilCode.length != 6) {
			resultEmailCode.innerText = "인증코드 6자리를 입력해주세요.";
		}
		
		else if(emilCode != receiveEmailCode){
			resultEmailCode.innerText = "인증코드가 일치하지 않습니다.";
		}
		else {
			resultEmailCode.innerText = "인증성공";
			resultEmailCode.style.color = "green";
			inputEmailCode.readOnly = true;
			emailAuthOk = true;
		}

	});

	// 전화번호 입력
	inputHp.addEventListener('focusout', function() {
		let hp = this.value;

		if (hp == "") {
			resultHp.innerText = "필수 정보입니다.";
			resultHp.style.color = "red";
			return;
		}

		else if (!reHp.test(hp)) {
			resultHp.innerText = "형식에 맞지 않는 번호입니다.";
			resultHp.style.color = "red";
			return;
		}
		
		// AJAX 전송

		let jsonData = { "hp":hp }
		 
		ajaxAPI("user/checkHp", jsonData, "post").then((response) => {

			if (response == null)
				alert('Request fail...');

			else if (response.result == 1) {
				resultHp.innerText = "이미 사용중인 번호입니다.";
				resultHp.style.color = "red";
			}
			
			else {
				resultHp.innerText = "";
				resultHp.style.color = "green";
				hpOk = true;
			}

		}).catch((errorMsg) => {
			console.log(errorMsg)
		});
	});
	
	// 주소 검색
	btnSearchAddr.addEventListener('click', function() { postcode();});
	
	// 폼 전송
	form.addEventListener('submit', function(e) {
		let okList = [uidOk, passOk, confirmPassOk, nameOk, nickOk, emailOk, emailAuthOk, hpOk];
		
		for (i of inputList){ i.focus(); }
		
		for (i in inputList){
			
			if(!okList[i]){
				inputList[i].focus();
				e.preventDefault();
				return;
			}
			
		}
	});
}