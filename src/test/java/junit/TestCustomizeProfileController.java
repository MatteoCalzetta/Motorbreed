package junit;

import com.example.motorbreedfinal.controller.CustomizeProfileController;
import com.example.motorbreedfinal.controller.LoginController;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.AccountBean;
import com.example.motorbreedfinal.view1.fagioli.LoginBean;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;


/** @author : Matteo Calzetta matricola 0273429
*/

class TestCustomizeProfileController {

    @Test
    void testChangeFirstName(){
        String newFirstName = "Username";
        String actualFirstName = "Matteo";
        String changedUsername;

        //avere un logged user
        LoginBean loginBean = new LoginBean();
        loginBean.setEmail("matteo.calzetta@students.uniroma2.eu");
        loginBean.setPassword("Matteo99");
        LoginController loginController = new LoginController();

        try {
            loginController.login(loginBean);
        } catch (FailedLoginException | SQLException e) {
            e.printStackTrace();
        }

        AccountBean accountBean = new AccountBean();
        accountBean.setFirstName("Matteo");
        accountBean.setLastName("Calzetta");
        accountBean.setEmail("matteocalzetta@students.uniroma2.eu");

        CustomizeProfileController customizeProfileController = new CustomizeProfileController();
        customizeProfileController.changeFirstName(accountBean);
        changedUsername = LoggedUser.getInstance().getAccount().getFirstName();

        //reset del cambiamento
        accountBean.setFirstName(actualFirstName);
        customizeProfileController.changeFirstName(accountBean);

        String expected = newFirstName;
        assertEquals(expected,changedUsername);
    }
}



