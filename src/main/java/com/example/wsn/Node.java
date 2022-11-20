package com.example.wsn;

import javafx.collections.ObservableList;

public class Node{
    int n ;
    float Rcode;
    float Pamp;
    float Energie;
    public Node(int n, float Rcode,float Pamp){
        this.Pamp=Pamp;
        this.Rcode=Rcode;
        this.n=n;
    }

    public float getEnergie() {
        return Energie;
    }

    public void setEnergie(float energie) {
        Energie = energie;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public float getRcode() {
        return Rcode;
    }

    public void setRcode(float Rcode) {
        this.Rcode = Rcode;
    }

    public float getPamp() {
        return Pamp;
    }

    public void setPamp(float Pamp) {
        this.Pamp = Pamp;
    }


}
