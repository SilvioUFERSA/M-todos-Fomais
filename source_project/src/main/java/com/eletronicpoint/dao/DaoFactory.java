package com.eletronicpoint.dao;

public class DaoFactory{

    public static IEmployeeDAO createEmployeeDAO(){
        return new EmployeeDAO();
    }

    public  static  IPointRegisterDAO createRegisterDAO(){
        return  new PointRegisterDAO();
    }
}
