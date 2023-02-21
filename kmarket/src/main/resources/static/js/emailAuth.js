/**
 * 이름 : 서정현
 * 날짜 : 2023-02-16
 * 내용 : 이메일 인증 JS
 */

function emailAuth(url){
	// Regex
    let reEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	let reEmailCode = /^[0-9]+$/;

	// 이메일 인증 코드
    let receiveEmailCode = null;

    // input
    const inputEmail = document.querySelector('input[name=email]');
	const inputEmailCode = document.querySelector('input[name=emailAuthCode]');

    // Button
    const btnEmailAuth = document.getElementById('btnEmailAuth');		// 이메일 인증번호 받기
    const btnEmailConfirm = document.getElementById('btnEmailConfirm'); // 이메일 인증코드 확인

    // Validation Result
    const resultEmail = document.querySelector('.msgEmail');
    const resultEmailCode = document.querySelector('.msg_emailAuth');

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
        const inputVal = document.querySelector('.val');
        let key = inputVal.name; // 아이디 찾기시 : name, 비밀번호 찾기시 : uid
        let val = inputVal.value;

        if (emailOk == true) {
            alert('이미 이메일 전송을 완료했습니다.');
            return;
        }

        if (!reEmail.test(email)) {
            resultEmail.innerText = "형식에 맞지 않는 이메일 입니다.";
            return;
        }

        if (val.trim() == "") {
            let msgVal = inputVal.nextSibling.nextSibling;
            msgVal.innerText = (key == "name"? "이름을":"아이디를") + " 입력해주세요.";
            inputVal.focus();
            return;
        }

        emailOk = true;
        inputEmail.readOnly = true;
        resultEmail.innerText = "이메일 전송 중...";
        resultEmail.style.color = "green";

        // JsonData
        const jsonData = { "email": email };
        jsonData[key] = val;

        // AJAX
        ajaxAPI(url, jsonData, "POST").then((response) => {
            console.log(response);

            if (response == null) {
                alert('Request fail...');
                inputEmail.readOnly = false;
                emailOk = false;
            }

            else if (response.result == 0) {
                resultEmail.innerText = "등록된 정보가 없습니다. 다시 확인해주세요.";
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
            return;
        }

        if(emailAuthOk == false){
            resultEmailCode.innerText = "이메일 인증이 되지 않았습니다.";
            return;
        }

    });

    // 이메일 인증 코드 확인
    btnEmailConfirm.addEventListener('click', function() {
        let emailCode = inputEmailCode.value;

        console.log(emailCode);
        console.log(receiveEmailCode);

        if (emailAuthOk == true) {
            alert('이미 인증완료되었습니다.');
        }

        else if (emailCode.length != 6) {
            resultEmailCode.innerText = "인증코드 6자리를 입력해주세요.";
        }

        else if(emailCode != receiveEmailCode){
            resultEmailCode.innerText = "인증코드가 일치하지 않습니다.";
        }

        else {
            resultEmailCode.innerText = "인증성공";
            resultEmailCode.style.color = "green";
            inputEmailCode.readOnly = true;
            emailAuthOk = true;
        }
    });

}