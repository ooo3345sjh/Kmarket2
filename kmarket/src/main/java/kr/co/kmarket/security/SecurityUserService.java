package kr.co.kmarket.security;

/**
 * 날짜 : 2023/02/13
 * 이름 : 서정현
 * 내용 : 시큐리티 유저 서비스
 */

import kr.co.kmarket.dao.UserDAO;
import kr.co.kmarket.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SecurityUserService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserVO userType = userDAO.selectUser(username);

		UserVO user = null;
		if(userType != null){
			if(userType.getType() == 2){
				user = userDAO.selectSellerUser(username);
			}
			else {
				user = userDAO.selectGeneralUser(username);
			}

			return user;
		}

		throw new UsernameNotFoundException(username);
	}

}
