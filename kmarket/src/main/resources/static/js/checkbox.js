
    // 체크박스 모두 선택
    function selectAll(selectAll) {
        const checkboxes = document.getElementsByName('check');

        checkboxes.forEach((checkbox) => {
            checkbox.checked = selectAll.checked;
        })
    }

    $(function() {
        let checkObj = document.getElementsByName('check');
        let rowCount = checkObj.length;

        // 단일 체크 처리
        $("input[name='check']").click(function() {
            if($("input[name='check']:checked").length == rowCount) {
                $("input[name='allCheck']")[0].checked = true;
            } else {
                $("input[name='allCheck']")[0].checked = false;
            }
        });
    });

