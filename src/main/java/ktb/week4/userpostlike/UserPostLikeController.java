package ktb.week4.userpostlike;

import ktb.week4.user.CurrentUser;
import ktb.week4.user.User;
import ktb.week4.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userlikes/{postId}")
@RequiredArgsConstructor
public class UserPostLikeController {
    private final UserPostLikeService userPostLikeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUserPostLike(@PathVariable Long postId,
                                                @CurrentUser User user) {

        userPostLikeService.createUserPostLike(postId, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserPostLike(@PathVariable Long postId,
                                                @CurrentUser User user) {
        userPostLikeService.deleteUserPostLike(postId, user);
        return ResponseEntity.ok().build();
    }


}
