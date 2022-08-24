package com.haruhanjan.authentication.service;

//@Service
//@RequiredArgsConstructor
//public class UserService {
//    private final CustomUserRepository customUserRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public List<UserResponseDto> getAll() {
//        List<User> targets =customUserRepository.findAll();
//        return targets.stream()
//                .map(UserResponseDto::new)
//                .collect(Collectors.toList());
//    }
//
//    public UserResponseDto save(CreateUserRequestDto dto) {
//        User entity = dto.toEntity(passwordEncoder.encode(dto.getPassword()));
//        User saved = customUserRepository.save(entity);
//        return new UserResponseDto(saved);
//    }
//
//    @Transactional
//    public void delete(Long id) {
//        User target =customUserRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//        target.delete();
//    }
//}
