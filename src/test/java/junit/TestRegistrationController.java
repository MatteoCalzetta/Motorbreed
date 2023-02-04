package junit;

import com.example.motorbreedfinal.controller.RegistrationController;
import com.example.motorbreedfinal.model.exceptions.FailedRegistrationException;
import com.example.motorbreedfinal.view1.fagioli.RegistrationBean;

import static org.junit.jupiter.api.Assertions.assertThrows;

/** @author : Matteo Calzetta matricola 0273429
 */

class TestRegistrationController {

    @org.junit.jupiter.api.Test
    void testRegistrationExistingEmail(){

        RegistrationBean registrationBean = new RegistrationBean();
        registrationBean.setFirstName("Matteo");
        registrationBean.setLastName("Calzetta");
        registrationBean.setEmail("matteo.calzetta@students.uniroma2.eu");
        registrationBean.setRole("Buyer");
        registrationBean.setPassword("Matteo99");

        RegistrationController registrationController = new RegistrationController();

        assertThrows(FailedRegistrationException.class,()->registrationController.registration(registrationBean));
    }

}
