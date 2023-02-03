package com.example.motorbreedfinal.view2;

import com.example.motorbreedfinal.controller.ManageAdsController;
import com.example.motorbreedfinal.view1.fagioli.AdBean;
import com.example.motorbreedfinal.view1.fagioli.CarBean;
import com.example.motorbreedfinal.view2.utility.LinePrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ModifyAdControllerG2 {

    ManageAdsController manageAdsController;

    AdBean adBean;

    int previousPrice;
    int idAd;
    int numberClicks = 0;

    int idCar;

    String licencePlate;

    String toPrint;

    int index = 0;

    AdBean myAdbean;

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public void setManageAdsController(ManageAdsController manageAdsController){
        this.manageAdsController = manageAdsController;
    }

    public void showAd(int index) {

        int num = index +1 ;

        myAdbean = adBean;

        toPrint = "Showing ad num " + num + " of " + adBean.getAds().toArray().length;
        LinePrinter.getInstance().print(toPrint);

        toPrint = "Brand : " + adBean.getAds().get(index).getCar().getBrand() + "| Model : " + adBean.getAds().get(index).getCar().getModel() +
                "| Mileage : " + adBean.getAds().get(index).getCar().getMileage()+
                "| Matriculation Year : " + adBean.getAds().get(index).getCar().getImmatricolationYear() +
                "| Production Year : " + adBean.getAds().get(index).getCar().getProductionYear()+
                "| Transmission " + adBean.getAds().get(index).getCar().getTransmission() +
                "| Horsepower " + adBean.getAds().get(index).getCar().getHorsepower() +
                "| Fuel Type " + adBean.getAds().get(index).getCar().getFuelType();

        LinePrinter.getInstance().print(toPrint);


        toPrint = " CruiseControl : ";
        toPrint = toPrint.concat(String.valueOf(adBean.getAds().get(index).getCar().getDecorations().charAt(0) == '1'));


        toPrint = toPrint.concat(" KeylessSystem ");
        toPrint = toPrint.concat(String.valueOf(adBean.getAds().get(index).getCar().getDecorations().charAt(1) == '1'));


        toPrint = toPrint.concat(" HeatedSeats ");
        toPrint = toPrint.concat(String.valueOf(adBean.getAds().get(index).getCar().getDecorations().charAt(2) == '1'));

        toPrint = toPrint.concat(" LedHeadlights ");
        toPrint = toPrint.concat(String.valueOf(adBean.getAds().get(index).getCar().getDecorations().charAt(3) == '1'));


        toPrint = toPrint.concat(" ParkingSensors  ");
        toPrint = toPrint.concat(String.valueOf(adBean.getAds().get(index).getCar().getDecorations().charAt(4) == '1'));


        LinePrinter.getInstance().print(toPrint);

        toPrint = "| Insurance : ";
        if(adBean.getAds().get(index).getCar().isInsurance()){
            toPrint = toPrint.concat(" true ");
        }else{
            toPrint = toPrint.concat(" false ");
        }

        toPrint = "Price : " + adBean.getAds().get(index).getAdCost() + "| Location  :" + adBean.getAds().get(index).getAdLocation() +
                    "| Description : " + adBean.getAds().get(index).getAdDescription() +
                "| InsertionDate : " + adBean.getAds().get(index).getInsertionDate().substring(0, adBean.getAds().get(index).getInsertionDate().length()-18)+
        "| Certification :";

        if(adBean.getAds().get(index).isPriceCertificated()){
            toPrint = toPrint.concat(" true ");
        }else{
            toPrint = toPrint.concat(" false ");
        }

        LinePrinter.getInstance().print(toPrint);

        previousPrice = adBean.getAds().get(index).getAdCost();
        idAd = Integer.parseInt(adBean.getAds().get(index).getIdAd());
        numberClicks = adBean.getAds().get(index).getNumberOfClicks();
        idCar = Integer.parseInt(adBean.getAds().get(index).getCar().getIdCar());
        licencePlate = adBean.getAds().get(index).getCar().getLicencePlate();

        navigateAds();
    }

    public void setBean(AdBean adBean) {
        this.adBean = adBean;
    }

    public void navigateAds(){
        toPrint = "Press 0 to go on the previous Ad, 1 to go on the next one, anything to modify the current one";
        LinePrinter.getInstance().print(toPrint);

        try {
            switch (reader.readLine()){
                case "0":
                    showPreviousAd();
                    break;
                case "1":
                    showNextAd();
                    break;
                default:
                    modifyAd();
                    break;
            }
        }catch(IOException e){
            navigateAds();
        }

    }

    public void showNextAd() {
        this.index++;
        if(this.index >= adBean.getAds().toArray().length || this.index == -1) {
            this.index = 0;
        }
        showAd(index);
    }

    public void showPreviousAd() {
        this.index--;
        if(this.index >= adBean.getAds().toArray().length|| this.index == -1) {
            this.index = 0;
        }
        showAd(index);
    }

    private void modifyAd(){
        adBean = new AdBean();

        CarBean carBean = new CarBean();

        toPrint = "Do you want to modify Brand ? 1 for yes, anything otherwise";
        LinePrinter.getInstance().print(toPrint);

        try {

            carBean = extractBrand(carBean);

            toPrint = "Do you want to modify Model ? 1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            carBean = extractModel(carBean);

            toPrint = "Do you want to modify Mileage? 1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            carBean = extractMileage(carBean);

            toPrint = "Do you want to modify insurance? 1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            carBean = extractInsurance(carBean);

            toPrint = "Do you want to modify transmission? 1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            carBean = extractTransmission(carBean);

            toPrint = "Do you want to modify production Year ?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            carBean = extractProductionYear(carBean);

            toPrint = "Do you want to modify matriculation Year ?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            carBean = extractMatriculationYear(carBean);

            toPrint = "Do you want to modify horsepower?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            carBean = extractHP(carBean);

            toPrint = "Do you want to modify fuel type?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            carBean = extractFuelType(carBean);

            String decorations = "";
            String choice = "Insert 1 for yes, 0 for no";

            toPrint = "Do you want to modify CruiseControl Optional?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            if (reader.readLine().equals("1")) {

                LinePrinter.getInstance().print(choice);

                decorations = decorations.concat(reader.readLine());
            }else {
                decorations = decorations.concat(String.valueOf(myAdbean.getAds().get(index).getCar().getDecorations().charAt(0)));
            }

            toPrint = "Do you want to modify KeylessSystem Optional?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            if (reader.readLine().equals("1")) {
                LinePrinter.getInstance().print(choice);
                decorations = decorations.concat(reader.readLine());
            }
            else {
                decorations = decorations.concat(String.valueOf(myAdbean.getAds().get(index).getCar().getDecorations().charAt(1)));
            }

            toPrint = "Do you want to modify HeatedSeats Optional?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);

            if (reader.readLine().equals("1")) {
                LinePrinter.getInstance().print(choice);
                decorations = decorations.concat(reader.readLine());
            }else {
                decorations = decorations.concat(String.valueOf(myAdbean.getAds().get(index).getCar().getDecorations().charAt(2)));
            }


            toPrint = "Do you want to modify LedHeadlights?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            if (reader.readLine().equals("1")) {

                LinePrinter.getInstance().print(choice);

                decorations = decorations.concat(reader.readLine());
            }else {
                decorations = decorations.concat(String.valueOf(myAdbean.getAds().get(index).getCar().getDecorations().charAt(3)));
            }


            toPrint = "Do you want to modify ParkingSensors?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            if (reader.readLine().equals("1")) {

                LinePrinter.getInstance().print(choice);

                decorations = decorations.concat(reader.readLine());
            }else {
                decorations = decorations.concat(String.valueOf(myAdbean.getAds().get(index).getCar().getDecorations().charAt(4)));
            }


            toPrint = "Do you want to modify Description ?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);

            adBean = extractDescription();

            toPrint = "Do you want to modify Location?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            adBean = extractLocation();

            toPrint = "Do you want to modify Price?  1 for yes, anything otherwise";
            LinePrinter.getInstance().print(toPrint);


            if (reader.readLine().equals("1")) {
                toPrint = "Insert the new price";
                LinePrinter.getInstance().print(toPrint);

                adBean.setCost(Integer.parseInt(reader.readLine()));

            }else {
                adBean.setCost(myAdbean.getAds().get(index).getAdCost());
            }

            adBean.setPriceCertification(false);


            Date in = new Date();
            LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
            Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

            adBean.setInsertionDate(out);
            adBean.setIdAd(String.valueOf(idAd));
            adBean.setNumberOfClicks(numberClicks);
            adBean.setIdAd(String.valueOf(idAd));
            carBean.setLicencePlate(licencePlate);

            manageAdsController.modifyAd(adBean, carBean, idCar, decorations);

            this.showAd(0);

        }catch (IOException e){
            showAd(0);
        }
    }

    private AdBean extractLocation() throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new location";
            LinePrinter.getInstance().print(toPrint);

            adBean.setLocation(reader.readLine());
        }else {
            adBean.setLocation(myAdbean.getAds().get(index).getAdLocation());
        }
        return adBean;
    }

    private AdBean extractDescription() throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new description";
            LinePrinter.getInstance().print(toPrint);

            adBean.setDescription(reader.readLine());
        }else {
            adBean.setDescription(myAdbean.getAds().get(index).getAdDescription());
        }
        return adBean;
    }

    private CarBean extractFuelType(CarBean carBean) throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new value of fuel type";
            LinePrinter.getInstance().print(toPrint);

            carBean.setBeanFuelType(reader.readLine());

        }else {
            carBean.setBeanFuelType(myAdbean.getAds().get(index).getCar().getFuelType());
        }
        return carBean;
    }

    private CarBean extractHP(CarBean carBean) throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new value of horsepower";
            LinePrinter.getInstance().print(toPrint);

            carBean.setBeanHorsepower(Integer.parseInt(reader.readLine()));
        }else {
            carBean.setBeanHorsepower(myAdbean.getAds().get(index).getCar().getHorsepower());
        }
        return carBean;
    }

    private CarBean extractMatriculationYear(CarBean carBean) throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new value of matriculation Year";
            LinePrinter.getInstance().print(toPrint);

            carBean.setImmatricolationYear(reader.readLine());
        }else {
            carBean.setImmatricolationDate(myAdbean.getAds().get(index).getCar().getImmatricolationYear());
        }
        return carBean;
    }

    private CarBean extractProductionYear(CarBean carBean) throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new value of production Year";
            LinePrinter.getInstance().print(toPrint);

            carBean.setBeanProductionYear(reader.readLine());
        }else {
            carBean.setBeanProductionYear(myAdbean.getAds().get(index).getCar().getProductionYear());
        }
        return carBean;
    }

    private CarBean extractTransmission(CarBean carBean) throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new value of transmission";
            LinePrinter.getInstance().print(toPrint);

            carBean.setTransmission(reader.readLine());
        }else {
            carBean.setTransmission(myAdbean.getAds().get(index).getCar().getTransmission());
        }
        return carBean;
    }

    private CarBean extractInsurance(CarBean carBean) throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new value of insurance(true/false)";
            LinePrinter.getInstance().print(toPrint);

            carBean.setInsurance(Boolean.parseBoolean(reader.readLine()));
        }else {
            carBean.setInsurance(myAdbean.getAds().get(index).getCar().isInsurance());
        }
        return carBean;
    }

    private CarBean extractMileage(CarBean carBean) throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new value of mileage";
            LinePrinter.getInstance().print(toPrint);

            carBean.setCarBeanMileage(Integer.parseInt(reader.readLine()));
        }else {
            carBean.setCarBeanMileage(myAdbean.getAds().get(index).getCar().getMileage());
        }
        return carBean;
    }

    private CarBean extractModel(CarBean carBean) throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new value of model";
            LinePrinter.getInstance().print(toPrint);

            carBean.setCarBeanModel(reader.readLine());
        }else {
            carBean.setCarBeanModel(myAdbean.getAds().get(index).getCar().getModel());
        }
        return carBean;
    }

    private CarBean extractBrand(CarBean carBean) throws IOException {
        if (reader.readLine().equals("1")) {
            toPrint = "Insert the new value of brand";
            LinePrinter.getInstance().print(toPrint);

            carBean.setCarBeanBrand(reader.readLine());
        }else {
            carBean.setCarBeanBrand(myAdbean.getAds().get(index).getCar().getBrand());
        }
        return carBean;
    }
}


