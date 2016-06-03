/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Minh Nhat
 */
public class CTP_DichVuDTO {
 
    /*
     * Properties
     */

    private String maCTP_DV;
    private String maP_DV;
    private String maDV;
    private int soLuong;
    private double thanhTien;

    public CTP_DichVuDTO(String maCTP_DV, String maP_DV, String maDV, int soLuong, double thanhTien) {
        this.maCTP_DV = maCTP_DV;
        this.maP_DV = maP_DV;
        this.maDV = maDV;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }
    
    
    
    /*
    * Constructor
    */

    public CTP_DichVuDTO() {
    }
    
    /*
    * Getters and Setters
    */

    public String getMaCTP_DV() {
        return maCTP_DV;
    }

    public void setMaCTP_DV(String maCTP_DV) {
        this.maCTP_DV = maCTP_DV;
    }

    public String getMaP_DV() {
        return maP_DV;
    }

    public void setMaP_DV(String maP_DV) {
        this.maP_DV = maP_DV;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
}
