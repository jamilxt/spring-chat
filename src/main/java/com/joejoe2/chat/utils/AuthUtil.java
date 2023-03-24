package com.joejoe2.chat.utils;

import com.joejoe2.chat.data.UserDetail;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.WebSocketSession;

public class AuthUtil {
  public static boolean isAuthenticated() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
  }

  public static UserDetail currentUserDetail() throws AuthenticationException {
    if (!isAuthenticated())
      throw new InternalAuthenticationServiceException("has not been authenticated !");
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return (UserDetail) authentication.getPrincipal();
  }

  public static boolean isAuthenticated(WebSocketSession session) {
    Authentication authentication = (Authentication) session.getPrincipal();
    return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
  }

  public static UserDetail currentUserDetail(WebSocketSession session)
      throws AuthenticationException {
    if (!isAuthenticated(session))
      throw new InternalAuthenticationServiceException("has not been authenticated !");
    Authentication authentication = (Authentication) session.getPrincipal();
    return (UserDetail) authentication.getPrincipal();
  }

  public static void setCurrentUserDetail(UserDetail userDetail) {
    Authentication authentication =
        new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
