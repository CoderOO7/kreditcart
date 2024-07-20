package com.kreditcart.userservice.Services;

import com.kreditcart.userservice.Enums.SessionStatusEnum;
import com.kreditcart.userservice.Models.Session;
import com.kreditcart.userservice.Models.User;
import com.kreditcart.userservice.Repositories.SessionRepository;
import com.kreditcart.userservice.Repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionRepository sessionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public User userSignup(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));

            return userRepository.save(user);
        }

        return userOptional.get();
    }

    public Pair<User, MultiValueMap<String, String>> userLogin(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) {
            return null;
        }

        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        // Token generation

//        String payloads = "{\n" +
//                "    \"name\": \"Romeo\",\n" +
//                "    \"email\": \"knockknock@yourheart.com\",\n" +
//                "    \"expiration_date\": \"Tu meri saansein ban ja aur main teri dhadkan, agar ek bhi saath chhode to dono ki kahani khatam.\"\n" +
//                "}";
//
//        byte[] content = payloads.getBytes(StandardCharsets.UTF_8);

        long nowInMillis = System.currentTimeMillis();
        Date expiryTime =  new Date(nowInMillis + 10000);
        Map<String, Object> jwtData = new HashMap<>();
        jwtData.put("email", user.getEmail());
        jwtData.put("roles", user.getRoles());
        jwtData.put("expiryTime", expiryTime);
        jwtData.put("createdAt", new Date(nowInMillis));

        // secret key generation
        MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
        SecretKey secretKey = macAlgorithm.key().build();
        String token = Jwts.builder().claims(jwtData).signWith(secretKey).compact();

        Session session = new Session();
        session.setSessionStatus(SessionStatusEnum.ACTIVE);
        session.setUser(user);
        session.setToken(token);
        session.setExpiryTime(expiryTime);
        sessionRepository.save(session);

        // pass token in cookies
        // multiValueMap is wrapper of Map
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE, "jwt="+token);

        return new Pair<User, MultiValueMap<String, String>>(user, headers);
    }
}
