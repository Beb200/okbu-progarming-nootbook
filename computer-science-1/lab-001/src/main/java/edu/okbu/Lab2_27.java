package edu.okbu;

public class Lab2_27 {
    double x;
    double y;
    double z;

    public Lab2_27(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double x_power_of_z(){
        return Math.pow(x,z);
    }

    public double x_power_of_y_power_to_z(){
        return Math.pow(x,Math.pow(y,z));
    }

    public double Absolute_of_y(){
        return Math.abs(y);
    }

    public double Square_of_xy_to_power_z(){
        return Math.sqrt(Math.pow((x*y),z));
    }
}
