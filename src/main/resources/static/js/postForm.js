document.addEventListener("DOMContentLoaded", async () => {
    const postForm = document.querySelector(".post-form");
    const postTitle = document.querySelector("#title");
    const postContent = document.querySelector("#content");
    const postImage = document.querySelector("#image");
    const fileNameText = document.querySelector(".file-name");
    const helperText = document.querySelector(".helper-text");
    const pageTitle = document.querySelector(".post-title");
    const submitBtn = document.querySelector(".submit-btn");

    const urlParams = new URLSearchParams(window.location.search);
    const postId = urlParams.get("postId");
    const mode = urlParams.get("mode");
    const baseUrl = "http://localhost:8080";

    const titleEl = document.querySelector(".header h1");
    if (titleEl) {
        titleEl.style.cursor = "pointer";
        titleEl.addEventListener("click", () => {
            window.location.href = "./postList.html";
        });
    }

    if (mode === "edit") {
        pageTitle.textContent = "게시글 수정";
        submitBtn.textContent = "수정하기";
    } else {
        pageTitle.textContent = "게시글 작성";
        submitBtn.textContent = "등록하기";
    }

    if (mode === "edit" && postId) {
        try {
            const res = await fetch(`${baseUrl}/posts/${postId}`, {
                method: "GET",
                credentials: "include",
            });

            if (!res.ok) throw new Error("게시글 로드 실패");

            const post = await res.json();

            postTitle.value = post.title || "";
            postContent.value = post.content || "";

            if (post.postImages && post.postImages.length > 0) {
                const fileName = post.postImages[0].imageUrl.split("/").pop();
                fileNameText.textContent = `기존 파일: ${fileName}`;
            } else {
                fileNameText.textContent = "기존 파일 없음";
            }
        } catch (err) {
            console.error(err);
            alert("게시글 정보를 불러오는 중 문제가 발생했습니다.");
        }
    }

    postImage.addEventListener("change", (e) => {
        const file = e.target.files[0];
        fileNameText.textContent = file ? `선택된 파일: ${file.name}` : "파일 없음";
    });

    postForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        const title = postTitle.value.trim();
        const content = postContent.value.trim();
        const image = postImage.files[0];

        if (!title || !content) {
            helperText.textContent = "제목과 내용을 입력해주세요.";
            helperText.style.color = "red";
            return;
        }

        const formData = new FormData();
        formData.append("postTitle", title);
        formData.append("postContent", content);
        if (image) formData.append("files", image);

        try {
            let url = `${baseUrl}/posts`;
            let method = "POST";

            if (mode === "edit" && postId) {
                url = `${baseUrl}/posts/${postId}`;
                method = "PATCH";
            }

            const response = await fetch(url, {
                method,
                credentials: "include",
                body: formData,
            });

            if (!response.ok) {
                const error = await response.text();
                console.error("게시글 저장 실패:", error);
                helperText.textContent = "게시글 저장에 실패했습니다.";
                helperText.style.color = "red";
                return;
            }

            const result = await response.json();
            alert(mode === "edit" ? "게시글이 수정되었습니다." : "게시글이 등록되었습니다.");
            window.location.href = `./postDetail.html?postId=${mode === "edit" ? postId : result}`;
        } catch (e) {
            console.error("게시글 저장 오류:", e);
            helperText.textContent = "서버 통신 오류가 발생했습니다.";
            helperText.style.color = "red";
        }
    });
});
