package ktb.week4.userpostlike;

import ktb.week4.config.CurrentUser;
import ktb.week4.user.User;
import ktb.week4.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/userlikes/{postId}/users/{userId}")
@RequiredArgsConstructor
public class UserPostLikeController {
    private final UserPostLikeService userPostLikeService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUserPostLike(@PathVariable Long postId,
                                                @PathVariable Long userId) {

        User user = userService.getUserById(userId);

        userPostLikeService.createUserPostLike(postId, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserPostLike(@PathVariable Long postId,
                                                @PathVariable Long userId) {
        User user = userService.getUserById(userId);

        userPostLikeService.deleteUserPostLike(postId, user);
        return ResponseEntity.ok().build();
    }


}
