async function search(){
    LoadingWithMask();
    var data = new Object();
    var temp1 = JSON.parse(sessionStorage.getItem("tagList"));
    var temp2 = JSON.parse(sessionStorage.getItem("similarGames"));
    var temp3 = JSON.parse(sessionStorage.getItem("excludeGames"));
    data.tagList = temp1;
    data.similarGames = temp2;
    data.excludeGames = temp3;

    const json = JSON.stringify(data)

    console.log(data);

    const response = await fetch("http://localhost:8080/search/find", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        redirect: "follow",
        body: json,
    })
    .then((response) => response.json());

    sessionStorage.setItem("gameDto", JSON.stringify(response));

    closeLoadingWithMask();
    window.location.href = 'http://localhost:8080/search';
}

function LoadingWithMask() {
    //화면의 높이와 너비를 구합니다.
    var maskHeight = $(document).height();
    var maskWidth  = window.document.body.clientWidth;

    //화면에 출력할 마스크를 설정해줍니다.
    var mask       = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
    var loadingImg = '';

    loadingImg += "<div id='loadingImg' style='z-index:8000'>";
    loadingImg += " <img src='/Fidget-spinner.gif' style='position: relative; z-index:9000; display: block; margin: 0px auto; width:200px; height:200px;'/>";
    loadingImg += "</div>";

    //화면에 레이어 추가
    $('body')
        .append(mask)
    $('#searchBox')
        .append(loadingImg)


    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
    $('#mask').css({
            'width' : maskWidth
            , 'height': maskHeight
            , 'opacity' : '0.3'
    });

    //마스크 표시
    $('#mask').show();

    //로딩중 이미지 표시
    $('#loadingImg').show();
}

function closeLoadingWithMask() {
    $('#mask, #loadingImg').hide();
    $('#mask, #loadingImg').empty();
}