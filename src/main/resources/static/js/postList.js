document.addEventListener("DOMContentLoaded", async () => {
    const baseUrl = "http://localhost:8080";
    const postListContainer = document.querySelector(".post-list");
    const createBtn = document.querySelector(".create-btn");

    createBtn.addEventListener("click", () => {
        window.location.href = "./postForm.html?mode=create";
    });

    const titleEl = document.querySelector(".header h1");

    if (titleEl) {
        titleEl.style.cursor = "pointer";
        titleEl.addEventListener("click", () => {
            window.location.href = "./postList.html";
        });
    }


    async function loadPosts(page = 0) {
        try {
            const res = await fetch(`${baseUrl}/posts?page=${page}&size=10&sort=createdAt,DESC`, {
                method: "GET",
                credentials: "include",
            });

            if (!res.ok) throw new Error("게시글 목록 로드 실패");
            const data = await res.json();

            renderPostList(data.content);
        } catch (err) {
            console.error("게시글 목록 로드 오류:", err);
            postListContainer.innerHTML = `<p class="error">게시글을 불러오는 중 오류가 발생했습니다.</p>`;
        }
    }

    function renderPostList(posts) {
        postListContainer.innerHTML = "";

        if (!posts || posts.length === 0) {
            postListContainer.innerHTML = `<p class="no-post">아직 등록된 게시글이 없습니다.</p>`;
            return;
        }

        posts.forEach(post => {
            const postCard = document.createElement("div");
            postCard.classList.add("post-card");

            const createdAt = new Date(post.createdAt).toLocaleString("ko-KR", { timeZone: "Asia/Seoul" });

            postCard.innerHTML = `
                <div class="post-header">
                    <h3 class="post-title">${sanitize(post.title)}</h3>
                    <p class="post-date">${createdAt}</p>
                </div>
                <p class="post-info">좋아요 ${post.likeCount ?? 0} · 댓글 ${post.commentCount ?? 0} · 조회수 ${post.viewCount ?? 0}</p>
                <hr class="divider" />
                <div class="writer-info">
                    <div class="profile-img small">
                        <img src="${getProfileUrl(post.user)}" alt="작성자 프로필" />
                    </div>
                    <p class="writer-name">${sanitize(post.user?.nickName ?? "익명")}</p>
                </div>
            `;

            postCard.addEventListener("click", () => {
                window.location.href = `./postDetail.html?postId=${post.postId}`;
            });

            postListContainer.appendChild(postCard);
        });
    }

    function getProfileUrl(user) {
        if (!user || !user.imageUrl) return "/assets/default-profile.png";
        const url = user.imageUrl;
        if (url.startsWith("/files")) {
            return `${baseUrl}${url}`;
        } else {
            return `${baseUrl}/files/${url}`;
        }
    }

    function sanitize(str) {
        return str
            ?.replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#039;");
    }

    loadPosts();
});
