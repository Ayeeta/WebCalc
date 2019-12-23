package com.example.webcalc;

public class Operations {

    double num_one;
    double num_two;

    public Operations(double num_one, double num_two) {
        this.num_one = num_one;
        this.num_two = num_two;
    }

    public double getNum_one() {
        return num_one;
    }

    public void setNum_one(double num_one) {
        this.num_one = num_one;
    }

    public double getNum_two() {
        return num_two;
    }

    public void setNum_two(double num_two) {
        this.num_two = num_two;
    }

    public double add(){
        return num_one + num_two;
    }

    public double subtract(){
        return num_one - num_two;
    }

    public  double multiply(){
        return num_one * num_two;
    }

    public double divide(){
        return num_one/num_two;
    }
}
