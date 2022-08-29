package com.haruhanjan.authentication.service.oauth2.google;

import com.haruhanjan.authentication.config.oauth2.GoogleOauth2Config;
import com.haruhanjan.authentication.dto.OAuthResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleLoginService {
    private final GoogleOauth2Config googleOauth2Config;

    public String getRedirectURL() {
        return googleOauth2Config.getRedirectURL();
    }
    public String requestTokenURL(String code) {
        String requestTokenURL = googleOauth2Config.requestTokenURL(code);

        Map<String, String> responseData = googleOauth2Config.getRequestTokenParam(code);


        String accessToken =
                Optional.ofNullable(new RestTemplate()
                                .postForEntity(requestTokenURL, responseData, OAuthResponseDTO.class)
                                .getBody())
                        .orElseThrow(NullPointerException::new)
                        .getAccessToken();


        log.info("access: {}", accessToken);

        return requestTokenURL;
    }
}
