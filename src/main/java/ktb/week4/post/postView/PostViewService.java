package ktb.week4.post.postView;

import ktb.week4.post.Post;
import ktb.week4.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostViewService {

    private final PostViewRepository postViewRepository;

    public PostView getPostViewById(Long postId) {
        return postViewRepository.findById(postId)
                .orElseThrow(()-> new IllegalArgumentException("Post view not found"));
    }

    public void updateCommentCount(Long postId, boolean isDeleted) {
        PostView postView = getPostViewById(postId);
        if (isDeleted) {
            postView.downCommentCount();
        } else {
            postView.upCommentCount();
        }
    }

    public void updateLikeCount(Long postId, boolean isDeleted) {
        PostView postView = getPostViewById(postId);

        if (isDeleted) {
            postView.downLikeCount();
        } else {
            postView.upLikeCount();
        }
    }

    public void updateViewsCount(Long postId) {
        PostView postView = getPostViewById(postId);
        postView.updateViewCount();
    }
}
