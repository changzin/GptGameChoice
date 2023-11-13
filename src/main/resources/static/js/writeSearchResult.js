function writeSearchResult(){
    document.querySelector("#game_table").innerHTML += `
        <div class="card p-3 m-3">
            <div class = "row d-flex justify-content-between">
                <div class="col-9 h3">${gameDto.gameName}</div>
                <div class="col-3 float-right">
                    <input class="form-check-input" type="checkbox" onclick="checkSimilarGames(this)" value= ${gameDto.gameName} id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">유사한 게임 탐색</label>
                </div>
            </div>
            <dl class="row m-0">
                <dt class="col-sm-3 strong">가격</dt>
                <dd class="col-sm-9">${gameDto.gamePrice}</dd>
                <dt class="col-sm-3">평가</dt>
                <dd class="col-sm-9">${gameDto.gameReview}</dd>
                <dt class="col-sm-3">태그</dt>
                <dd class="col-sm-9">${gameDto.gameTag}</dd>
                <dt class="col-sm-3">상세 설명</dt>
                <dd class="col-sm-9">${gameDto.gameDescription}</dd>
            </dl>
             <a href=${gameDto.steamLink} class="btn btn-primary">Steam 에서 보기</a>
        </div>`;

    addExcludedGames();
    for(i = 0; i < excludeGames.length; i++){
        document.querySelector("#excludeGamesBox").innerHTML +=
            `<div class="excludeGame-row text-center py-1"><a href="#">${excludeGames[i]}</a></div>`;
    }
}


function addExcludedGames(){
    excludeGames.push(gameDto.gameName);
    sessionStorage.setItem("excludeGames", JSON.stringify(excludeGames));
}

function checkSimilarGames(box){
    if (box.checked){
        similarGames.push(gameDto.gameName);
    }
    else{
        similarGames.pop();
    }
    sessionStorage.setItem("similarGames", JSON.stringify(similarGames));
}