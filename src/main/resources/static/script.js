'use strict'

const BASE_URL = "http://172.29.176.200:8081";

function changeAuthStatus(user) {
    user.isAuthorised = !user.isAuthorised;
}

function addLike(postId) {
    posts.forEach(post => {
        if (post.id === postId) {
            post.likes++;
            return;
        }
    });
}


//HomeWork-58
const splashBtn = document.getElementById("splashBtn");
splashBtn.addEventListener("click", hideSplashScreen);
const splash = document.getElementsByClassName("splash");
function showSplashScreen() {
    splash[0].hidden = false;
}
function hideSplashScreen() {
    splash[0].hidden = true;
}


function timeFormat(timestamp) {
    const date = new Date(timestamp)
    return ("Date: "+date.getDate()+
    "/"+(date.getMonth()+1)+
    "/"+date.getFullYear()+
    " "+date.getHours()+
    ":"+date.getMinutes()+
    ":"+date.getSeconds());
}

function createCommentElement(comment) {
    const newComment = document.createElement('div');
    newComment.id = comment.id;
    newComment.innerHTML = '<p> ' + comment.user.name + ': ' + comment.text + '</p>' +
        '<p> ' +timeFormat(comment.dateTime) + '</p>';
    return newComment;
}

function createPostElement(post) {
    const newPost = document.createElement('div');
    newPost.classList.add('card');
    newPost.classList.add('mt-1');
    newPost.id = 'post'+post.id;
    newPost.style = 'width: 18rem';
    newPost.innerHTML = '<img src= "' + post.imageLink + '" class="card-img-top" alt="#">\n'+
     '<div class="heart" hidden ><span  class="mx-2 text-danger text-center" style="font-size:6rem"><i class="bi bi-heart-fill"></i></span></div>';
    const cardBody = document.createElement('div');
    cardBody.classList.add('card-body');
    // console.log(newPost);
    newPost.append(cardBody);

    cardBody.innerHTML =
        '<span  class="like h2 mx-2 muted"><i class="bi bi-heart-fill"></i></span>' +
        '<span  class="chat h2 mx-2 data-bs-toggle="modal" data-bs-target="#newcomment" " ><i class="bi bi-chat "></i></span>' +
        '<span  class="bookmark h2 mx-2 muted "><i class="bi bi-bookmark ms-auto"></i></span>' +
        '<h3> ' +post.user.name +':'+ post.description + '</h3>\n' +
        '<p> ' +timeFormat( post.dateTime )+ '</p>\n' +
        
    '<div class="chatForm" hidden>' +
    '<div class="commentList" id="cl'+post.id+'">' + 
        //comments
        
    '</div > ' +
        
    '<form class="commentForm">' +
    '<div class="mb-3">'+
          '<input type="textarea" class="form-control"  id="commenttext" name="text" required>'+
    ' </div>' +
    '<div class="mb-3">' +
        '<input type="text" class="form-control " hidden  value="11" name="userId">' +
    '</div>' +
    '<div class="mb-3">' +
        '<input type="text" class="form-control " hidden  value='+post.id+' name="postId">' +
    '</div>' +
        '<button type="submit"  class="btn btn-primary">Send</button>' +
    '</form> </div>';

    return newPost;
}


function addLikeFunction(postElement){
    const likeElements = postElement.getElementsByClassName("like");
    const likeButton = likeElements[0];
    likeButton.addEventListener("click", function (event) {
        event.preventDefault();
        if (likeButton.classList.contains('text-danger')) {
            likeButton.classList.add("muted");
            likeButton.classList.remove("text-danger");
            return
        }
        if (likeButton.classList.contains('muted')) {
            likeButton.classList.add("text-danger");
            likeButton.classList.remove("muted");
        }
    });

    const likeImg = postElement.getElementsByClassName("card-img-top")[0];
    likeImg.addEventListener("dblclick", function (event){
        event.preventDefault();
        const imgHeart = postElement.getElementsByClassName("heart")[0];

        imgHeart.hidden = false;
        setTimeout(function () {
            imgHeart.hidden = true;
        }, 2000);

        if (likeButton.classList.contains('muted')) {
            likeButton.classList.add("text-danger");
            likeButton.classList.remove("muted");
        }
    });

}

function addBookmarkFunction(postElement){
    const bookmarkElement = postElement.getElementsByClassName("bookmark")[0];
    bookmarkElement.addEventListener("click", function (event){
        event.preventDefault();
        if (bookmarkElement.firstChild.classList.contains('bi-bookmark-check')) {
            bookmarkElement.firstChild.classList.remove('bi-bookmark-check')
            bookmarkElement.firstChild.classList.add('bi-bookmark')
        } else {
            bookmarkElement.firstChild.classList.remove('bi-bookmark')
            bookmarkElement.firstChild.classList.add('bi-bookmark-check')
        }
    })
}

function addChatFunction(postElement){
    const chatElement = postElement.getElementsByClassName("chat")[0];
    chatElement.addEventListener("click", function (event){
        event.preventDefault();

        const chatform = event.target.parentElement.parentElement.lastElementChild;
        if (chatform.hidden) {
            chatform.hidden = false;
            let id = postElement.getAttribute("id");
            getComment(id);
        } else {
            chatform.hidden = true;
            chatform.firstElementChild.innerHTML="";
        }
    });

    const CommentForm = postElement.getElementsByClassName("commentForm")[0];
    CommentForm.addEventListener("submit", function (event){
        event.preventDefault();
        const form = event.target;
        const data = new FormData(form);
        sendComment(data);
        CommentForm.firstElementChild.firstElementChild.value="";
    })
}


function addPost(post) {
    const postElement = createPostElement(post);
    addLikeFunction(postElement);
    addChatFunction(postElement);
    addBookmarkFunction(postElement);
    document.getElementsByClassName("posts")[0].prepend(postElement);
}

function sendComment(formData) {
    axios.post(BASE_URL + '/comment/', formData)
        .then(function (response) {
            console.log(response.data)
            addComment(response.data);
            return response.data;
        })
        .catch(function (error) {
            console.log('error from back: ' + error);
        });
}
function getComment(id){
    let postId = id.replace(/post/gi, "");
    axios.get(BASE_URL + '/comment/'+postId)
        .then(function (response) {
            addComments(response.data);
            return response.data;
        })
        .catch(function (error) {
            console.log('error from back: ' + error);
        });
}
function addComments(comments) {
    comments.forEach((comment)=>{
        const commentElement = createCommentElement(comment);
        document.getElementById("cl"+comment.publication.id).append(commentElement);
    });
}
function addComment(comment) {
        const commentElement = createCommentElement(comment);
        document.getElementById("cl"+comment.publication.id).append(commentElement);
}


var register = document.getElementById('signup')
    var myName = document.getElementById('name')
    
register.addEventListener('shown.bs.modal', function () {
    myName.focus()
});

const newUser = document.getElementById('registerform');
newUser.addEventListener('submit', function (e){
    e.preventDefault();
    const form = e.target;
    const formData = new FormData(form);
    var object = {};
    formData.forEach(function(value, key){
        object[key] = value;
    });
    var json = JSON.stringify(object);
    console.log(json);
    sendNewUser(json);
});

function sendNewUser(formData) {
    axios.post(BASE_URL + '/user/add', formData,{
        headers: {
            "Content-Type": "application/json"
        }
    })

        .then(function (response) {
            alert('User: ' + response.data)
        })
        .catch(function (error) {
            alert('User не создан: ' + error);
        });
}

    var myModal = document.getElementById('newpost');
    var myInput = document.getElementById('postfile');
    
    myModal.addEventListener('shown.bs.modal', function () {
        myInput.focus()
    });





    const sendPostBtn = document.getElementById('sendform');
    sendPostBtn.addEventListener('submit', function (e){
        e.preventDefault();
        const form = e.target;
        const formData = new FormData(form);
        sendPost(formData);
    });

     function sendPost(formData) {
        axios.post(BASE_URL + '/publications/add', formData,
            {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            }
        )
            .then(function (response) {
                // console.log('from back: ' + response.data.user.name)
                addPost(response.data);
            })
            .catch(function (error) {
                console.log('error from back: ' + error);
            });
    }

function getAllPosts(){
    axios.get(BASE_URL+'/publications/')
        .then(function (response) {
            response.data.forEach(
                (post) => { addPost(post) });
            // console.log(response)
        })
        .catch(function (error) {
            console.log(error);
        })
}

getAllPosts();

      

