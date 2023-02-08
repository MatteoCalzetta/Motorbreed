package junit;

import com.example.motorbreedfinal.controller.LoginController;
import com.example.motorbreedfinal.view1.fagioli.AccountHomepageBean;
import com.example.motorbreedfinal.view1.fagioli.LoginBean;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author : Matteo Calzetta matricola 0273429
 */

class TestLoginController {

    @org.junit.jupiter.api.Test
    void testLogin(){
        LoginBean loginBean = new LoginBean();
        loginBean.setEmail("matteo.calzetta@students.uniroma2.eu");
        loginBean.setPassword("Matteo99");
        LoginController loginController = new LoginController();

        try {
            AccountHomepageBean accountHomepageBean = loginController.login(loginBean);
            String firstName = accountHomepageBean.getFirstName();
            String lastName = accountHomepageBean.getLastName();
            String role = accountHomepageBean.getRole();
            assertEquals("Matteo",firstName);
            assertEquals("Calzetta",lastName);
            assertEquals("Buyer",role);

        } catch (FailedLoginException | SQLException e) {
            e.printStackTrace();
        }
    }
}
