writeTag();

function selectTagButton(selectedTagId){
    var tagDtoList = JSON.parse(sessionStorage.getItem("tagDtoList"));
    var tagList = JSON.parse(sessionStorage.getItem("tagList"));
    const selectedTag = tagDtoList.find(function(item) {    return item.tagId === selectedTagId    });
    const idx = tagDtoList.indexOf(selectedTag);

    console.log(selectedTagId);

    tagList.push(selectedTag);
    tagDtoList.splice(idx, 1);

    sessionStorage.setItem("tagList", JSON.stringify(tagList));
    sessionStorage.setItem("tagDtoList", JSON.stringify(tagDtoList));

    document.querySelector("#selected_tag_table").innerHTML = '';
    document.querySelector("#tag_table").innerHTML = '';
    writeTag();
}

function unselectTagButton(selectedTagId){
    var tagDtoList = JSON.parse(sessionStorage.getItem("tagDtoList"));
    var tagList = JSON.parse(sessionStorage.getItem("tagList"));
    const selectedTag = tagList.find(function(item) {    return item.tagId === selectedTagId    });
    const idx = tagList.indexOf(selectedTag);

    console.log(selectedTagId);

    tagDtoList.push(selectedTag);
    tagList.splice(idx, 1);

    tagDtoList.sort((a, b) => {
        if (a.tagId < b.tagId) return -1;
        if (a.tagId > b.tagId) return 1;

        return 0;
    });
    sessionStorage.setItem("tagList", JSON.stringify(tagList));
    sessionStorage.setItem("tagDtoList", JSON.stringify(tagDtoList));

    document.querySelector("#selected_tag_table").innerHTML = '';1
    document.querySelector("#tag_table").innerHTML = '';
    writeTag();
}

function writeTag(){
    var tagDtoList = JSON.parse(sessionStorage.getItem("tagDtoList"));
    var tagList = JSON.parse(sessionStorage.getItem("tagList"));

    tagDtoList.forEach(tagDto => {
        if (tagDto.check==false){
            document.querySelector("#tag_table").innerHTML += `
                        <div class="p-2 tag_box">
                            <button id="${tagDto.tagId}" onclick="selectTagButton(${tagDto.tagId})" class="btn btn-primary tag_button">${tagDto.tagName}</button>
                        </div>`;
        }
    })

    tagList.forEach(tagDto => {
        if (tagDto.check==false){
            document.querySelector("#selected_tag_table").innerHTML += `
                        <div style="height: 40px" class="p-2 tag_box">
                            <button id="${tagDto.tagId}" onclick="unselectTagButton(${tagDto.tagId})" style="font-size: 15px" class="btn btn-primary tag_button">${tagDto.tagName}</button>
                        </div>`;
        }
    })
}