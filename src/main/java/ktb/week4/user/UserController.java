package ktb.week4.user;

import jakarta.validation.Valid;
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

    /**
     * @CurrentUser User user 사용예정
     * 현재 로그인 기능 미구현으로 Pathvariable로 받아옴
     */

    @PostMapping
    public ResponseEntity<Long> signup(@Valid @ModelAttribute SignUpRequest request) {

        Long userId = userService.signUp(request);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        UserResponse response = userService.getUsers(user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{userId}/profile-image")
    public ResponseEntity<?> updateProfileImage(@RequestParam("file") MultipartFile file,
                                                @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        userService.updateProfileImage(file, user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}/nickname")
    public ResponseEntity<?> updateNickname(@Valid @RequestBody nickNameUpdateRequest request,
                                            @PathVariable Long userId) {
        User user = userService.getUserById(userId);
        userService.updateNickname(request, user);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{userId}/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody passwordUpdateRequest request,
                                            @PathVariable Long userId) {

        User user = userService.getUserById(userId);
        userService.updatePassword(request, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

}
