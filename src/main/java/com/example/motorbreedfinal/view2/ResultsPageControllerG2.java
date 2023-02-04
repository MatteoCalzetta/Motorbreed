package com.example.motorbreedfinal.view2;

import com.example.motorbreedfinal.controller.ResearchController;
import com.example.motorbreedfinal.model.Ad;
import com.example.motorbreedfinal.model.users.AccountObserver;
import com.example.motorbreedfinal.model.users.AccountSubject;
import com.example.motorbreedfinal.model.users.LoggedUser;
import com.example.motorbreedfinal.view1.fagioli.AccountBean;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.EmailBean;
import com.example.motorbreedfinal.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ResultsPageControllerG2 implements AccountObserver {
    private List<Ad> ads;
    private int index = 0;

    String toPrint = "";

    public void setAds(AdBean adBean) {
        initialize();
        this.ads = adBean.getAds();
        try {
            displayAd();
        } catch (IOException e) {
            //towrite
        }
    }

    private String createDecorations(boolean bool, String toPrint){
        if(bool){
            toPrint += "✔,";
        }else {
            toPrint += "x,";
        }

        return toPrint;
    }

    private void displayAd() throws IOException {
        if(this.index >= ads.toArray().length || this.index == -1) {
            this.index = 0;
        }
        Ad ad = ads.get(this.index);
        toPrint = "Ad "+ (this.index+1) +" of "+ ads.size() + "\n";
        toPrint += "Brand = " + ad.getCar().getBrand();
        toPrint += ", model = " + ad.getCar().getModel();
        toPrint += ", price = " + ad.getAdCost() + "€";
        toPrint += ", seller is "+ ad.getSeller().getFirstName() + " " + ad.getSeller().getLastName();
        toPrint += ", location = " + ad.getAdLocation();
        toPrint += ", mileage = "+ad.getCar().getMileage();
        toPrint += ", horsepower = "+ad.getCar().getHorsepower();
        toPrint += ", fuel type = "+ad.getCar().getFuelType();
        toPrint += ", insertion date = " +ad.getInsertionDate();
        toPrint += ", production year = " +ad.getCar().getProductionYear();
        toPrint += ", transmission " + ad.getCar().getTransmission();
        toPrint += ", heated seats: ";

        toPrint = createDecorations(ad.getCar().getDecorations().charAt(0) == '1', toPrint);

        toPrint += " parking sensors: ";

        toPrint = createDecorations(ad.getCar().getDecorations().charAt(1) == '1', toPrint);

        toPrint += " led headlights: ";

        toPrint = createDecorations(ad.getCar().getDecorations().charAt(2) == '1', toPrint);

        toPrint += " cruise control: ";

        toPrint = createDecorations(ad.getCar().getDecorations().charAt(3) == '1', toPrint);

        toPrint += " heated seats: ";

        toPrint = createDecorations(ad.getCar().getDecorations().charAt(4) == '1', toPrint);


        LinePrinter.getInstance().print(toPrint);
        toPrint = "\nPress 0 to show ad description, 1 to contact seller via email, 2 to buy car, 3 for next ad, 4 for previous ad: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        switch(reader.readLine()){
            case "0":
                toPrint = ad.getAdDescription();
                LinePrinter.getInstance().print(toPrint);
                displayAd();
                break;
            case "1":
                openEmail();
                displayAd();
                break;
            case "2":
                AccountBean accountBean = new AccountBean();
                accountBean.setFirstName(LoggedUser.getInstance().getBuyer().getFirstName());
                accountBean.setLastName(LoggedUser.getInstance().getBuyer().getLastName());
                accountBean.setEmail(LoggedUser.getInstance().getBuyer().getEmail());
                AdBean adBean = new AdBean();
                adBean.setIdAd(ads.get(this.index).getIdAd());
                adBean.setCost(ads.get(this.index).getAdCost());
                adBean.setSeller(ads.get(this.index).getSeller().getFirstName() + ads.get(this.index).getSeller().getLastName());
                ResearchController.getInstance().payment(accountBean, adBean);
                break;
            case "3":
                this.index++;
                displayAd();
                break;
            case "4":
                this.index--;
                displayAd();
                break;
            default:
                break;
        }
        //manca descrizione

    }

    private void openEmail() {
        String body;
        String password;
        toPrint = "Insert message to send: ";
        LinePrinter.getInstance().print(toPrint);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            body = reader.readLine();
            toPrint = "insert email's password: ";
            LinePrinter.getInstance().print(toPrint);
            password = reader.readLine();
            EmailBean emailBean = new EmailBean();
            emailBean.setFromEmail(LoggedUser.getInstance().getBuyer().getEmail());
            emailBean.setToEmail(ads.get(index).getSeller().getEmail());
            emailBean.setDescription(body);
            emailBean.setPassword(password);
            if(emailBean.validation()){
                ResearchController.getInstance().sendEmail(emailBean);
                toPrint = "Email sent succesfully! going back to the ad";
                LinePrinter.getInstance().print(toPrint);
                displayAd();
            }
            else{
                toPrint = "there was an error sending your email. press 0 to start again or anything to go back to the ad: ";
                LinePrinter.getInstance().print(toPrint);
                if(reader.readLine().equals("0")) {
                    openEmail();
                } else {
                    displayAd();
                }
            }
        } catch (IOException e) {
            //towrite
        }

    }

    public void initialize(){
        AccountSubject.attach(this);
    }

    @Override
    public void update() {
        toPrint = "Order bought successfully! Going back to my orders page. . .";
        LinePrinter.getInstance().print(toPrint);
        BuyerHomepageControllerG2 buyerHomepageControllerG2 = new BuyerHomepageControllerG2();
        buyerHomepageControllerG2.myProfile();
    }
}
