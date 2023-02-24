package kr.co.kmarket.security;

import kr.co.kmarket.dao.UserDAO;
import kr.co.kmarket.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AuthFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String username = request.getParameter("username");
        UserVO userVO = userDAO.selectGeneralUser(username);

        if(userVO.getWdate() != null && !"".equals(userVO.getWdate())){
            response.sendRedirect(request.getContextPath()+"/user/login?error=w");
            return;
        }
        response.sendRedirect(request.getContextPath()+"/user/login?error");
    }
}
