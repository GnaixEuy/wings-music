package cn.limitless.wingsmusic.filter;


import cn.hutool.core.util.ObjectUtil;
import cn.limitless.wingsmusic.config.WebSecurityConfig;
import cn.limitless.wingsmusic.entity.User;
import cn.limitless.wingsmusic.exception.BizException;
import cn.limitless.wingsmusic.exception.ExceptionType;
import cn.limitless.wingsmusic.service.UserService;
import cn.limitless.wingsmusic.utils.RedisCache;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/12
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserService userService;
    private final RedisCache redisCache;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserService userService,
                                  RedisCache redisCache) {
        super(authenticationManager);
        this.userService = userService;
        this.redisCache = redisCache;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(WebSecurityConfig.HEADER_STRING);
        if (header == null || !header.startsWith(WebSecurityConfig.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        User user = this.redisCache.getCacheObject(header.replace("Bearer ", ""));
        if (ObjectUtil.isNull(user)) {
            throw new BizException(ExceptionType.UNAUTHORIZED);
        }
        UsernamePasswordAuthenticationToken authenticationToken = this.getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        if (header != null) {
            String username = JWT.require(Algorithm.HMAC512(WebSecurityConfig.SECRET.getBytes()))
                    .build()
                    .verify(header.replace(WebSecurityConfig.TOKEN_PREFIX, ""))
                    .getSubject();
            if (username != null) {
                User user = this.redisCache.getCacheObject(header.replace("Bearer ", ""));
                return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
            }
        }
        return null;
    }
}
