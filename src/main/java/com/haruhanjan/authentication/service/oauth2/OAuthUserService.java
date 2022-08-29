package com.haruhanjan.authentication.service.oauth2;

import com.haruhanjan.authentication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final OAuth2UserService oAuth2UserService;

    private final OAuth2ServiceFactory oAuth2ServiceFactory;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String socialServiceName = userRequest.getClientRegistration().getRegistrationId();

        oAuth2ServiceFactory.oAuth2Service(socialServiceName);


        return null;
    }
}
