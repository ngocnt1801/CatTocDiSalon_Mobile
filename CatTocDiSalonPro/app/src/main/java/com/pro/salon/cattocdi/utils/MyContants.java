package com.pro.salon.cattocdi.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.pro.salon.cattocdi.models.Appointment;
import com.pro.salon.cattocdi.models.Category;
import com.pro.salon.cattocdi.models.Customer;
import com.pro.salon.cattocdi.models.Salon;
import com.pro.salon.cattocdi.models.Service;
import com.pro.salon.cattocdi.models.enums.AppointmentStatus;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class MyContants {
   public static String TOKEN = "";
    public static final String BASE_URL = "http://192.168.43.138/cattocdi.api/";
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
    //public static  final ArrayList<Customer> LISTCUSTOMER = new ArrayList<Customer>();





    /*public static final Service[] SERVICES_1 = {
            new Service(1, "Cắt tóc nam", 30000, 15),
            new Service(2, "Cắt tóc nữ", 100000, 30),
    };

    public static final Service[] SERVICES_2 = {
            new Service(3, "Nhuộm tóc nam", 150000, 60),
            new Service(4, "Nhuộm tóc nữ", 400000, 120),
            new Service(5, "Phủ bóng", 250000, 90),
            new Service(6, "Nhuộm Henna", 280000, 120),
            new Service(7, "Móc Light", 200000, 60),
    };

    public static final Service[] SERVICES_3 = {
            new Service(8, "Uốn tóc (Lạnh)", 300000, 120),
            new Service(9, "Uốn tóc (Nóng)", 300000, 120),
            new Service(10, "Duỗi tóc", 250000, 90),
            new Service(11, "Uốn 1 điểm trên tóc", 450000, 120),
            new Service(12, "Duỗi 1 điểm trên tóc", 450000, 120),
    };

    public static final Service[] SERVICES_4 = {
            new Service(13, "Rửa mặt", 20000, 15),
            new Service(14, "Tỉa chân mày", 50000, 35),
            new Service(15, "Cạo râu", 15000, 15),
            new Service(16, "Ráy tai", 15000, 15),
            new Service(17, "Gội đầu", 10000, 15),

    };
    public static final Category[] CATEGORIES = {
            new Category(1, "Cắt tóc", toList(SERVICES_1)),
            new Category(2, "Nhuộm màu", toList(SERVICES_2)),
            new Category(3, "Uốn và duỗi", toList(SERVICES_3)),
            new Category(4, "Dịch vụ khác", toList(SERVICES_4)),

    };
    public static final Salon[] SALONS = {
            new Salon("Beautiful Hair", 4.8f, false, 40, toList(CATEGORIES),10.858228, 106.629373, "12 Dương Thị Mười, Q.12", 60, "0123456789", "beautifulhair@gmail.com"),

    };
    public static final Service[] SERVICES_APPOINTMENT = {
            SERVICES_1[1],
            SERVICES_3[3],
            SERVICES_2[1],
            SERVICES_4[4]
    };
    public static final Appointment[] APPOINTMENTS = {
          new Appointment(SALONS[0], Timestamp.valueOf("2018-11-2 14:00:00"), Timestamp.valueOf("2018-11-2 16:00:00"), toList(SERVICES_APPOINTMENT), 40, AppointmentStatus.NOT_APPROVED),
            new Appointment(SALONS[0], Timestamp.valueOf("2018-12-5 16:00:00"), Timestamp.valueOf("2018-12-5 17:00:00"), toList(SERVICES_APPOINTMENT), 30, AppointmentStatus.NOT_APPROVED),
            new Appointment(SALONS[0], Timestamp.valueOf("2018-10-20 9:00:00"), Timestamp.valueOf("2018-10-20 10:30:00"), toList(SERVICES_APPOINTMENT), 25, AppointmentStatus.APPROVED),

    };
    public static  final Customer[] CUSTOMERS = {
            new Customer("c1","Ngọc Nguyễn", "012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c2","Nhi Nguyễn", "012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c3","Phong Nguyễn", "012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c4","Đạt Trần", "012456789","abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c5","Phong Nguyễn", "012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c6","Thiện Lâm","012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c7","Nam Đặng","012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c8","Lộc Ngô","012456789", "abc@gmail.com",toList(APPOINTMENTS))
    };*/
   // public static final  ArrayList<Customer> CUSTOMERS2 = new ArrayList<Customer>()
   /* public static final ArrayList<Customer> CUSTOMERS2 = new ArrayList<Customer>(Arrays.asList(
            new Customer("c1","Ngọc Nguyễn", "012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c2","Nhi Nguyễn", "012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c3","Phong Nguyễn", "012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c4","Đạt Trần", "012456789","abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c5","Phong Nguyễn", "012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c6","Thiện Lâm","012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c7","Nam Đặng","012456789", "abc@gmail.com",toList(APPOINTMENTS)),
            new Customer("c8","Lộc Ngô","012456789", "abc@gmail.com",toList(APPOINTMENTS))

    ));*/
    private static <T> ArrayList<T> toList(T[] array) {
        ArrayList<T> list = new ArrayList<>();
        for (T item :
                array) {
            list.add(item);
        }
        return list;
    }


}
