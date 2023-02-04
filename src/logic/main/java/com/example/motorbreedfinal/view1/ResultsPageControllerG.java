package com.example.motorbreedfinal.view1;

import com.example.motorbreedfinal.controller.ResearchController;
import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.users.AccountObserver;
import com.example.motorbreedfinal.model.users.AccountSubject;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.AccountBean;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.EmailBean;
import com.example.motorbreedfinal.view1.fagioli.FavouritesBean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.Rating;

import java.util.List;
public class ResultsPageControllerG implements AccountObserver {


    @FXML
    private Label lblIndex;

    @FXML
    private Rectangle background;

    @FXML
    private Button btnContactSeller;

    @FXML
    private Button btnDescription;

    @FXML
    private Button btnDwnPayment;

    @FXML
    private Label lblBrand;

    @FXML
    private Label lblFavourites;

    @FXML
    private Label lblFuel;

    @FXML
    private Label lblGoogleMaps;

    @FXML
    private Label lblHP;

    @FXML
    private Label lblInsertionDate;

    @FXML
    private Label lblIsPriceEvaluated;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblMileage;

    @FXML
    private Label lblModel;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblProductionDate;

    @FXML
    private Label lblSeller;

    @FXML
    private Label lblTransmission;

    @FXML
    private Label lblDescription;
    @FXML
    private Rating rtngRating;

    @FXML
    private Pane paneDescription;

    @FXML
    private Pane paneEmail;

    @FXML
    private ImageView carImage;

    @FXML
    private Button btnCloseDescriptionPane;

    @FXML
    private Button btnCloseEmailPane;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfMessageEmail;

    @FXML
    private TextField tfNameEmail;

    @FXML
    private TextField tfPhoneNumber;

    @FXML
    private CheckBox cbCruiseControl;

    @FXML
    private CheckBox cbHeatedSeats;

    @FXML
    private CheckBox cbKeyless;

    @FXML
    private CheckBox cbLed;

    @FXML
    private CheckBox cbParkingSensors;

    @FXML
    private Button btnSendEmail;
    @FXML
    private Label lblInvalidEmail;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private Pane panePasswordpane;

    List<Ad> ads;
    int index = 0;

    public void displayAd() {
        if(this.index >= ads.toArray().length || this.index == -1) {
            this.index = 0;
        }
        Ad ad = ads.get(this.index);
        lblPrice.setText(String.valueOf(ad.getAdCost()));
        lblSeller.setText(ad.getSeller().getFirstName() + ad.getSeller().getLastName());
        lblDescription.setText(ad.getAdDescription());
        lblLocation.setText(ad.getAdLocation());
        lblBrand.setText(ad.getCar().getBrand());
        lblModel.setText(ad.getCar().getModel());
        lblHP.setText(String.valueOf(ad.getCar().getHorsepower()));
        lblMileage.setText(String.valueOf(ad.getCar().getMileage()));
        lblInsertionDate.setText(ad.getInsertionDate());
        lblTransmission.setText(ad.getCar().getTransmission());
        lblProductionDate.setText(ad.getCar().getProductionYear());
        lblFuel.setText(ad.getCar().getFuelType());
        lblIndex.setText(index+1 + " of " + ads.size());

        cbHeatedSeats.setSelected(ad.getCar().getDecorations().charAt(0) == '1');
        cbParkingSensors.setSelected(ad.getCar().getDecorations().charAt(1) == '1');
        cbLed.setSelected(ad.getCar().getDecorations().charAt(2) == '1');
        cbCruiseControl.setSelected(ad.getCar().getDecorations().charAt(3) == '1');
        cbKeyless.setSelected(ad.getCar().getDecorations().charAt(4) == '1');
    }


    public void setAd(AdBean adBean) {
        ads = adBean.getAds();
        displayAd();
    }

    public void initialize(){
        AccountSubject.attach(this);
    }

    @FXML
    void openDescription(ActionEvent event) {
        paneDescription.setVisible(true);
    }

    @FXML
    void openEmailPane(ActionEvent event) {
        tfNameEmail.setText(LoggedUser.getInstance().getBuyer().getFirstName() + " " + LoggedUser.getInstance().getBuyer().getLastName());
        tfEmail.setText(LoggedUser.getInstance().getBuyer().getEmail());
        paneEmail.setVisible(true);
    }
    @FXML
    void previousAd(ActionEvent event) {
        this.index--;
        displayAd();
    }

    @FXML
    void nextAd(ActionEvent event) {
        this.index++;
        displayAd();
    }

    @FXML
    void openMotorbreedPay(ActionEvent event) {
        AccountBean accountBean = new AccountBean();
        accountBean.setFirstName(LoggedUser.getInstance().getBuyer().getFirstName());
        accountBean.setLastName(LoggedUser.getInstance().getBuyer().getLastName());
        accountBean.setEmail(LoggedUser.getInstance().getBuyer().getEmail());
        AdBean adBean = new AdBean();
        adBean.setIdAd(ads.get(this.index).getIdAd());
        adBean.setCost(ads.get(this.index).getAdCost());
        adBean.setSeller(ads.get(this.index).getSeller().getFirstName() + ads.get(this.index).getSeller().getLastName());
        ResearchController.getInstance().payment(accountBean, adBean);
    }

    @FXML
    void closeDescription(ActionEvent event) {
        paneDescription.setVisible(false);
    }

    @FXML
    void closeEmail(ActionEvent event) {
        paneEmail.setVisible(false);
    }

    @FXML
    void setHomepage(ActionEvent event) {
        FXMLLoader loader = FxmlLoader.setPage("BuyerHomepage");
        BuyerHomepageControllerG buyerHomepageControllerG = loader.getController();
        buyerHomepageControllerG.setNameSurnameTF(LoggedUser.getInstance().getBuyer().getFirstName(),
                LoggedUser.getInstance().getBuyer().getLastName());
    }

    @Override
    public void update() {
        FxmlLoader.setPage("BuyerUserPage");
    }

    @FXML
    public void addFavorites(MouseEvent event){
        FavouritesBean favouritesBean = new FavouritesBean();
        favouritesBean.setIdAd(ads.get(this.index).getIdAd());
        AdBean adBean = new AdBean();
        adBean.setAds((List<Ad>) ads.get(index));
        favouritesBean.setIdBuyer(LoggedUser.getInstance().getBuyer().getIdAccount());
        ResearchController.getInstance().addFavorites(favouritesBean, adBean);
    }

    @FXML
    void insertPassword(ActionEvent event){
        EmailBean emailBean = new EmailBean();
        emailBean.setPassword(pfPassword.getText());
        sendEmail(emailBean);
    }

    @FXML
    void openPasswordPane(ActionEvent event){
        panePasswordpane.setVisible(true);
    }

    void sendEmail(EmailBean emailBean) {
        emailBean.setFromEmail(tfEmail.getText());
        emailBean.setToEmail(ads.get(index).getSeller().getEmail());
        emailBean.setDescription(tfMessageEmail.getText());
        if(emailBean.validation()){
            ResearchController.getInstance().sendEmail(emailBean);
            paneEmail.setVisible(false);
        }
        else{
            lblInvalidEmail.setVisible(true);
        }
    }

    @FXML
    void enableDisableEmail(ActionEvent event) {
        lblInvalidEmail.setVisible(false);
    }


}





