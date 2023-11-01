console.log(bnoVal);
async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method:'post',
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.getElementById('cmtPostBtn').addEventListener('click',()=>{
const cmtText = document.getElementById('cmtText').value; 
const cmtWriter = document.getElementById('cmtWriter').innerText;
if (cmtText == "" || cmtText == null) {
    alert("댓글을 입력해주세요");
    document.getElementById('cmtText').focus();
    return false;
}else{
    let cmtData={
        bno : bnoVal,
        writer : cmtWriter,
        content : cmtText
    };
    console.log(cmtData);
    postCommentToServer(cmtData).then(result =>{
        console.log(result);
        if (result == 1) {
            alert("댓글 등록 성공");
            document.getElementById('cmtText').value='';
        }
        getCommentList(bnoVal);
    })
}
})
async function spreadCommentListFromServer(bno, page){
    try {
        const resp = await fetch('/comment/'+bno+'/'+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }

}

//무조건 처음 뿌릴때는 첫페이지 값을 뿌려야 함.
function getCommentList(bno, page=1){
    spreadCommentListFromServer(bno, page).then(result=>{
        console.log(result); //ph 객체 pgvo, totalCount, cmtList

        let ul = document.getElementById('cmtListArea');
        //다시 댓글을 뿌릴 때 기존 값 삭제 1page일 경우만
        if (result.cmtList.length > 0) {
            if (page == 1) {
                ul.innerText="";
            }
            for(let cvo of result.cmtList){
            let li = `<li data-cno="${cvo.cno}" data-writer="${cvo.writer}">`;
            li += `<div><div class="fw-bole">${cvo.writer}</div>`;
            li += `<input type="text" value="${cvo.content}"><br>`;
            li +=`<span class="badge rounded-pill text-bg-dark">${cvo.regAt}</span>`;
            li += `<button type="button" class="modBtn btn btn-dark"  data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
            li += `<button type="button" class="delBtn btn btn-dark">삭제</button></div></li>`;
            ul.innerHTML += li;
        }
        //댓글 페이징 코드
        let moreBtn = document.getElementById('moreBtn');
        console.log(moreBtn);
        //db에서 pgvo + list 같이 가져와야 값을 줄 수 있음
        if (result.pgvo.pageNo < result.endPage) {
            moreBtn.style.visibility = 'visible'; //버튼 표시
            moreBtn.dataset.page = page + 1;
        }else{
            moreBtn.style.visibility = 'hidden'; //버튼 숨김
            
        }

        }else{
            let li = `<li>Comment List Empty</li>`;
            ul.innerHTML = li;
        }
    })
}

async function removeCommentToServer(cno,writer){
    try {
        const url = '/comment/'+cno+'/'+writer;
        const config = {
            method : 'delete'
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click', (e)=>{
    console.log(e.target);
    
    if (e.target.classList.contains('delBtn')) {
        console.log('삭제버튼 클릭');
        let li = e.target.closest('li');
        let cnoVal = li.dataset.cno;
        let writerId = li.dataset.writer;
        
        removeCommentToServer(cnoVal,writerId).then(result =>{
            if (result == 1) {
                alert('댓글 삭제 성공~!!');
            }else{
                alert('작성자가 아닙니다')
            }
            getCommentList(bnoVal);
        })
    }else if(e.target.classList.contains('modBtn')){
        let li = e.target.closest('li');
        //nextSibling() : 같은 부모의 다음 형제 객체를 반환
        let cmtText = li.querySelector('.fw-bole').nextSibling;
        console.log(cmtText);
        // let writerId = li.dataset.writer;
        // console.log(writerId);
        //기존 내용 모달창에 반영(수정하기 편하게...)
        document.getElementById('cmtTextMod').value = cmtText.value;
        //cmtModBtn에 data-cno 달기
        document.getElementById('cmtModBtn').setAttribute('data-cno',li.dataset.cno);
        document.getElementById('cmtModBtn').setAttribute('data-writer',li.dataset.writer);
    }else if(e.target.id == 'cmtModBtn'){
        let li = e.target.closest('li');
        let cmtDataMod ={
        cno: e.target.dataset.cno,
        content: document.getElementById('cmtTextMod').value,
        writer : e.target.dataset.writer,
        };
        console.log(cmtDataMod);
        editCommentToserver(cmtDataMod).then(result =>{
            if (parseInt(result)) {
                //모달창 닫기
                document.querySelector('.btn-close').click();
                console.log("result >>> ",result);
                if (result == "1") {
                    alert('댓글 수정 완료');
                }else if(result == "2"){
                    alert('작성자가 아닙니다');
                }
                getCommentList(bnoVal);
            }
        })
        
    }else if(e.target.id == 'moreBtn'){
        getCommentList(bnoVal, parseInt(e.target.dataset.page));
    }
})

async function editCommentToserver(cmtDataMod){
    try {
        const url = '/comment/'+cmtDataMod.cno;
        const config = {
            method:'put',
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtDataMod)
        }
        const resp = await fetch(url,config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}