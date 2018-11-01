package com.pro.salon.cattocdi.utils;

import com.pro.salon.cattocdi.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class MyContants {
    public static final String USERNAME_TEST = "salon";
    public static final String PASSWORD_TEST = "123";
    public static final int FRAGMENT_ABOVE = 1;
    public static final int FRAGMENT_BELOW = 2;
    public static final int APPOINTMENT_SMALL = 1;
    public static final int APPOINTMENT_FULL = 2;
    public static final int HOME_PAGE = 1;
    public static final int SCHEDULE_PAGE = 2;
    public static final int CLIENT_PAGE = 3;
    public static final int SIGNUP_PAGE = 4;
    public static final int MANAGER_SERVICE_PAGE = 5;
    public static final int PROFILE_PAGE = 6;
    public static final int PREVIEW_PAGE = 7;
    public static  final ArrayList<Customer> LISTCUSTOMER = new ArrayList<Customer>();
    public static final Customer[] CUSTOMERS = {
            new Customer("Ngọc Nguyễn", "31/10/2018", "3:00PM", "4:00PM"),
            new Customer("Nhi Nguyễn", "1/11/2018", "3:00PM", "4:00PM"),
            new Customer("Phong Nguyễn", "1/11/2018", "4:00PM", "5:00PM"),
            new Customer("Đạt Trần", "2/11/2018", "3:00PM", "4:00PM"),
            new Customer("Phong Nguyễn", "1/10/2018", "4:00PM", "5:00PM"),
            new Customer("Thiện Lâm", "12/10/2018", "4:00PM", "5:00PM"),
            new Customer("Nam Đặng", "13/10/2018", "4:00PM", "5:00PM"),
            new Customer("Lộc Ngô", "16/10/2018", "4:00PM", "5:00PM")

};


}
