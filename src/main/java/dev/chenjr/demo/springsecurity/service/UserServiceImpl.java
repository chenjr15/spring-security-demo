package dev.chenjr.demo.springsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;
    private HashMap<String, String> hardCodedUsers;

    @PostConstruct
    public void initUsers() {
        // 不能在构造函数用Autowired 的属性
        // Bean初始化时候的执行顺序： 构造方法 -> @Autowired -> @PostConstruct
        this.hardCodedUsers = new HashMap<>();
        this.hardCodedUsers.put("admin", "admin");
        this.hardCodedUsers.put("jack", "123456");
        // 加密密码
        this.hardCodedUsers.replaceAll((u, v) -> passwordEncoder.encode(v));
    }



    /**
     * 根据用户名加载用户详情信息
     *
     * @param username 用户名
     * @return 用户详情
     * @throws UsernameNotFoundException 找不到用户的时候
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 目前只是硬编码的用户
        if (!this.hardCodedUsers.containsKey(username)) {
            throw new UsernameNotFoundException("Username not found!");
        }
        return new User(username, hardCodedUsers.get(username), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
