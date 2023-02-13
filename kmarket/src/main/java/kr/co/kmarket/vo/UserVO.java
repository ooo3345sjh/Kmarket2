package kr.co.kmarket.vo;

/*
 * 날짜 : 2023/02/09
 * 이름 : 서정현
 * 내용 : 회원정보 객체
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO implements UserDetails {

    private String uid;
    private String pass;
    private String name;
    private int gender;
    private String hp;
    private String email;
    private int type = 1;
    private int point;
    private int level;
    private String zip;
    private String addr1;
    private String addr2;
    private String regip;
    private String wdate;
    private String rdate;

    private String company;
    private String ceo;
    private String bizRegNum;
    private String comRegNum;
    private String tel;
    private String manager;
    private String managerHp;
    private String fax;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 계정이 갖는 권한 목록
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + type));
        return authorities;
    }

    @Override
    public String getPassword() {
        // 계정이 갖는 비밀번호
        return pass;
    }

    @Override
    public String getUsername() {
        // 계정이 갖는 아이디
        return uid;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정 만료 여부(true: 만료X, false: 만료O)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠김 여부(true: 잠김X, false: 잠김O)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 계정 비밀번호 만료여부(true: 만료X, false: 만료O)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 계정 활성화 여부(true: 활성화, false: 비활성화)
        return true;
    }
}
