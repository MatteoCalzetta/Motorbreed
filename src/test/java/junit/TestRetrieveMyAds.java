package junit;

import com.example.motorbreedfinal.controller.ManageAdsController;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.model.users.Seller;

import static org.junit.jupiter.api.Assertions.assertTrue;

/** @author : Luigi Talamo matricola 0272522
 */

/* The test verifies that all the ads inserted by the Seller "Luigi Talamo" have recieved the price certification
    of the application due to the fact they used the price calculated by the Evaluator

    The test is made to fail because not all the ads inserted by the current Seller do have the price
    certification flag set to "1"
 */

class TestRetrieveMyAds {

    @org.junit.jupiter.api.Test
    void test(){
        ManageAdsController manageAdsController = new ManageAdsController();

        Seller seller = new Seller();
        seller.setIdAccount("1");
        seller.setEmail("luigi.talamo31@gmail.com");
        seller.setFirstName("Luigi");
        seller.setLastName("Talamo");

        LoggedUser.getInstance().setSeller(seller);

        manageAdsController.retrieveMyAds();

        int lenght = manageAdsController.getAdBean().getAds().toArray().length;

        for(int i = 0; i<lenght; i++){
            assertTrue(manageAdsController.getAdBean().getAds().get(i).isPriceCertificated());
        }
    }
}
