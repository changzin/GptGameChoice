var searchMode = false;
sortTag();
function writeSortTag(){
    searchMode = false;
    sortTag();
    writeTag();
}

function sortTag(){
    if (searchMode){
        tagDtoList = tagDtoList.sort(function(a, b) {
                return b.lcs - a.lcs;
            });
    }
    else{
        tagDtoList = tagDtoList.sort(function(a, b) {
                return a.tagId - b.tagId;
            });
    }
    return;
}

function searchTag(){
    searchMode = true;
    const searchInput = document.querySelector("#search-input");
    tagDtoList.forEach(tagDto => { tagDto.lcs = lcs(tagDto.tagName, searchInput.value)});

    if (searchInput.value===''){
        searchMode = false;
    }
    sortTag();
    for(i = 0; i < tagDtoList.length; i++){
        tagDtoList[i].lcs = tagDtoList.length-i;
    }
    writeTag();
}

function lcs(string1, string2){
    const s1 = string1.length;
    const s2 = string2.length;
    const dp = Array.from(new Array(s1 + 1), () => new Array(s2 + 1));
    for (let i = 0; i <= s1; i++) {
      dp[i][0] = 0;
    }
    for (let j = 0; j <= s2; j++) {
      dp[0][j] = 0;
    }

    for (let i = 1; i <= s1; i++) {
      for (let j = 1; j <= s2; j++) {
        if (string1.charAt(i - 1) === string2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[s1][s2];
}

function selectTagButton(selectedTagId){
    const selectedTag = tagDtoList.find(function(item) {    return item.tagId === selectedTagId    });
    const idx = tagDtoList.indexOf(selectedTag);

    tagList.push(selectedTag);
    tagDtoList.splice(idx, 1);

    sessionStorage.setItem("tagList", JSON.stringify(tagList));
    sessionStorage.setItem("tagDtoList", JSON.stringify(tagDtoList));

    writeTag();
}

function unselectTagButton(selectedTagId){
    const selectedTag = tagList.find(function(item) {    return item.tagId === selectedTagId    });
    const idx = tagList.indexOf(selectedTag);

    tagDtoList.push(selectedTag);
    tagList.splice(idx, 1);

    sortTag();
    sessionStorage.setItem("tagList", JSON.stringify(tagList));
    sessionStorage.setItem("tagDtoList", JSON.stringify(tagDtoList));
    writeTag();
}

function writeTag(){
    document.querySelector("#selected_tag_table").innerHTML = '';
    document.querySelector("#tag_table").innerHTML = '';
    tagDtoList.forEach(tagDto => {
        document.querySelector("#tag_table").innerHTML += `
                    <div class="p-2 tag_box">
                        <button id="${tagDto.tagId}" onclick="selectTagButton(${tagDto.tagId})" class="btn btn-primary tag_button">${tagDto.tagName}</button>
                    </div>`;
    })

    tagList.forEach(tagDto => {
        document.querySelector("#selected_tag_table").innerHTML += `
                    <div class="p-2 tag_box">
                        <button id="${tagDto.tagId}" onclick="unselectTagButton(${tagDto.tagId})" class="btn btn-primary tag_button">${tagDto.tagName}</button>
                    </div>`;
    })
}