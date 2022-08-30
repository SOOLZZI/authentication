package com.haruhanjan.authentication.service.oauth2;

import com.haruhanjan.authentication.dto.OAuthUserDTO;
import com.haruhanjan.authentication.entity.User;
import com.haruhanjan.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuthUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserService userService;
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService;

    private final UserMapperFactory oAuth2ServiceFactory;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        //현재 로그인한 서비스
        String socialServiceName = userRequest.getClientRegistration().getRegistrationId();

        Map<String, Object> userAttributes = oAuth2UserService.loadUser(userRequest).getAttributes();
        UserMapper mapper = oAuth2ServiceFactory.userMapper(socialServiceName);
        OAuthUserDTO authUserDTO = mapper.mapToDTO(userAttributes);
        User user = userService.saveIfNone(authUserDTO);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getAuthority().name())),
                mapper.mapToTokenAttribute(userAttributes),
                "accountId"
        );
    }
}
