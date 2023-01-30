package com.example.motorbreedfinal.view2;

import com.example.motorbreedfinal.view2.utility.LinePrinter;

public class SellerHomepageControllerG2 {

    private String toPrint;
    public void setNameSurname(String name, String lastName){
        toPrint = "Welcome "+name+ " "+lastName;
        LinePrinter.getInstance().print(toPrint);
    }

}
