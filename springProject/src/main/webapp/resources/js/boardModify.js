console.log("boardModify.js")
document.addEventListener('click', (e)=>{
    console.log(e.target);
    if (e.target.classList.contains('file-x')) {
        console.log("삭제 클릭");
        let btn = e.target.closest('button');
        let uuid = btn.dataset.uuid;
        removeFile(uuid).then(result =>{
            if (result == 1) {
                alert('파일 삭제 성공');
                e.target.closest('li').remove();
                location.reload();
            }else{
                alert('파일 삭제 실패')
            }
        })
    }
})

async function removeFile(uuid){
    try {
        const url = '/board/file/'+uuid;
        const config ={
            method:'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

