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
public class HangGiaCongDTO {
    /*
     * Properties
     */

    private String maLoaiGC;
    private String tenLoaiGC;
    private double donGia;

    public HangGiaCongDTO(String maLoaiGC, String tenLoaiGC, double donGia) {
        this.maLoaiGC = maLoaiGC;
        this.tenLoaiGC = tenLoaiGC;
        this.donGia = donGia;
    }
    
    
    /*
    * Constructor
    */
    public HangGiaCongDTO(){
        
    }
    
    /*
    * Getters and Setters
    */

    public String getMaLoaiGC() {
        return maLoaiGC;
    }

    public void setMaLoaiGC(String maLoaiGC) {
        this.maLoaiGC = maLoaiGC;
    }

    public String getTenLoaiGC() {
        return tenLoaiGC;
    }

    public void setTenLoaiGC(String tenLoaiGC) {
        this.tenLoaiGC = tenLoaiGC;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    
    

}
