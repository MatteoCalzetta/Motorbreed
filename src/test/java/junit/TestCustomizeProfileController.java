package junit;

import com.example.motorbreedfinal.controller.CustomizeProfileController;
import com.example.motorbreedfinal.model.exceptions.FailedProfileCustomizationException;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.AccountBean;
import org.junit.jupiter.api.Test;

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

        AccountBean accountBean = new AccountBean();
        //accountBean.setFirstName();
        //accountBean.setLastName();
        //accountBean.setEmail();

        /*try {
            CustomizeProfileController customizeProfileController = new CustomizeProfileController();
            customizeProfileController.changeUsername(accountBean);
            changedUsername = UserLogin.getInstance().getAccount().getUsername();

            //reset del cambiamento
            accountBean.setUsername(actualUsername);
            customizeProfileController.changeUsername(accountBean);

        } catch (FailedProfileCustomizationException e) {
            changedUsername = UserLogin.getInstance().getAccount().getUsername();
        }

        String expected = newUsername;
        assertEquals(expected,changedUsername);
*/
    }
}
