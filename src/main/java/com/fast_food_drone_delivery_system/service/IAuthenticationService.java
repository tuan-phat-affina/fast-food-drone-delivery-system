package com.fast_food_drone_delivery_system.service;

import com.fast_food_drone_delivery_system.dto.request.*;
import com.fast_food_drone_delivery_system.dto.response.AuthenticationResponse;
import com.fast_food_drone_delivery_system.dto.response.IntrospectResponse;
import com.fast_food_drone_delivery_system.entity.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public interface IAuthenticationService {
    IntrospectResponse introspectResponse(IntrospectRequest request) throws ParseException, JOSEException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse register(RegisterRequest request);

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;

    SignedJWT verifyToken(String token, boolean isRefresh) throws ParseException, JOSEException;

    String generateToken(User user);
}
