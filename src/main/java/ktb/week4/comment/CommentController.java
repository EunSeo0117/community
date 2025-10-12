package ktb.week4.comment;

import ktb.week4.config.CurrentUser;
import ktb.week4.config.CustomUserDetails;
import ktb.week4.post.postView.PostViewService;
import ktb.week4.user.User;
import ktb.week4.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static ktb.week4.comment.CommentDto.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final PostViewService postViewService;

    @PostMapping("/posts/{postId}/comments/users/{userId}")
    public void createComment(@PathVariable Long postId,
                              @RequestBody CommentCreateRequest request,
                              @PathVariable Long userId) {

        User user = userService.getUserById(userId);

        commentService.createComment(postId, request, user);
        postViewService.updateCommentCount(postId, false);
    }

    @PatchMapping("/comments/{commentId}/users/{userId}")
    public void updateComment(@PathVariable Long commentId,
                              @RequestBody CommentUpdateRequest request,
                              @PathVariable Long userId) {

        User user = userService.getUserById(userId);

        commentService.updateComment(commentId, request, user);
    }

    @DeleteMapping("/comments/{commentId}/users/{userId}")
    public void deleteComment(@PathVariable Long commentId,
                              @PathVariable Long userId) {

        User user = userService.getUserById(userId);

        commentService.deleteComment(commentId, user);
        postViewService.updateCommentCount(commentId, true);
    }
}
