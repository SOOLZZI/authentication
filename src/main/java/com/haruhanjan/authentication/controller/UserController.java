package com.haruhanjan.authentication.controller;

//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//    @GetMapping
//    public ResponseEntity<List<UserResponseDto>> getAll() {
//        List<UserResponseDto> result = userService.getAll();
//        return ResponseEntity.ok(result);
//    }
//
//    @PostMapping
//    public ResponseEntity<UserResponseDto> save(@RequestBody CreateUserRequestDto dto) {
//        UserResponseDto result = userService.save(dto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id){
//        userService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
