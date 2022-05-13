package cn.limitless.wingsmusic.service.impl;

import cn.limitless.wingsmusic.config.WebSecurityConfig;
import cn.limitless.wingsmusic.dto.token.TokenCreateRequest;
import cn.limitless.wingsmusic.dto.user.UserDto;
import cn.limitless.wingsmusic.dto.user.UserUpdateRequest;
import cn.limitless.wingsmusic.entity.User;
import cn.limitless.wingsmusic.exception.BizException;
import cn.limitless.wingsmusic.exception.ExceptionType;
import cn.limitless.wingsmusic.mapper.UserMapper;
import cn.limitless.wingsmusic.repository.UserRepository;
import cn.limitless.wingsmusic.service.UserService;
import cn.limitless.wingsmusic.utils.RedisCache;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/12
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Service
public class UserServiceImpl implements UserService {

    PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private UserMapper userMapper;

    private RedisCache redisCache;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = this.userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new BizException(ExceptionType.USER_NOT_FOUND);
    }

    @Override
    public UserDto get(String id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        return this.isUserExist(userOptional);
    }

    @Override
    public void delete(String id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        return this.userMapper
                .toDto(this.userRepository.save(this.userMapper
                        .updateEntity(this.userRepository.getById(id),
                                userUpdateRequest)));
    }

    @Override
    public Page<UserDto> search(Pageable pageable) {
        return null;
    }

    @Override
    public String createToken(TokenCreateRequest tokenCreateRequest) {
        User user = loadUserByUsername(tokenCreateRequest.getUsername());
        if (!passwordEncoder.matches(tokenCreateRequest.getPassword(), user.getPassword())) {
            throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.isEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLED);
        }
        if (!user.isAccountNonLocked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }
        String sign = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + WebSecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(WebSecurityConfig.SECRET.getBytes()));
        this.redisCache.setCacheObject(user.getUsername(), user, WebSecurityConfig.EXPIRATION_TIME, TimeUnit.MILLISECONDS);
        return sign;
    }

    @Autowired
    @Lazy
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    @Lazy
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    @Lazy
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private UserDto isUserExist(Optional<User> optionalUser) {
        if (optionalUser.isEmpty()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        } else {
            return this.userMapper.toDto(optionalUser.get());
        }
    }

    private void checkUserName(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new BizException(ExceptionType.USER_NAME_DUPLICATE);
        }
    }
}
