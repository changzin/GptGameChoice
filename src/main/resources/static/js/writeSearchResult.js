function writeSearchResult(){
    var gameDtoList = JSON.parse(sessionStorage.getItem("gameDtoList"));
    gameDtoList.forEach(gameDto => {
        document.querySelector("#game_table").innerHTML += `
            <tr>
                <td>${gameDto.gameName}</td>
                <td>${gameDto.gamePrice}</td>
                <td>${gameDto.gameReview}</td>
                <td>${gameDto.gameTag}</td>
                <td>${gameDto.gameDescription}</td>
            </tr>`;
    })
}