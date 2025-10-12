package ktb.week4.user;

import ktb.week4.config.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static ktb.week4.user.UserDto.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Long> signup(@RequestBody SignUpRequest request) {

        Long userId = userService.signUp(request);
        return ResponseEntity.ok(userId);
    }

    @PatchMapping("/profile-image")
    public ResponseEntity<?> updateProfileImage(@RequestParam("file") MultipartFile file,
                                                @CurrentUser User user) {
        userService.updateProfileImage(file, user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/nickname")
    public ResponseEntity<?> updateNickname(@RequestBody nickNameUpdateRequest request,
                                            @CurrentUser User user) {
        userService.updateNickname(request, user);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody passwordUpdateRequest request,
                                            @CurrentUser User user) {
        userService.updatePassword(request, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@CurrentUser User user) {
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

}
