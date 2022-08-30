package com.haruhanjan.authentication.service.oauth2;

import com.haruhanjan.authentication.dto.oauth2.OAuthUserDTO;
import com.haruhanjan.authentication.entity.User;

import java.util.Map;

/**
 * Attribute 에서 반환받은 데이터를 {@link User} 로 변환하는 인터페이스

 * @author Hyeonjun Park
 * @since 0.0.1
 */
public interface UserMapper {
    /**
     * @param attributes  attribute 데이터들을 K(String), V(Object) 형식으로 받음
     * @return 변환된 User 객체를 반환
     */
    OAuthUserDTO mapToDTO(Map<String, Object> attributes);

    Map<String, Object> mapToTokenAttribute(Map<String, Object> attributes);


}
