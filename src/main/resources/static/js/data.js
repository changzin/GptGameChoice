init();

function init(){
    var tagList = [];
    var similarGames = [];
    var excludeGames = [];

    sessionStorage.setItem("tagList", JSON.stringify(tagList));
    sessionStorage.setItem("similarGames", JSON.stringify(similarGames));
    sessionStorage.setItem("excludeGames", JSON.stringify(excludeGames));
    sessionStorage.setItem("tagDtoList", JSON.stringify(tagDtoList));
}




