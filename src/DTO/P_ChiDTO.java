/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.sql.Timestamp;

/**
 *
 * @author Minh Nhat
 */
public class P_ChiDTO {
    /*
     * Properties
     */

    private String maP_Chi = null;
    private Timestamp BaoCao = null;
    private int tongCong = 0;
    
    /*
    * Constructor
    */
    public P_ChiDTO(){
        
    }
    
    /*
    * Getters and Setters
    */

    public String getMaP_Chi() {
        return maP_Chi;
    }

    public void setMaP_Chi(String maP_Chi) {
        this.maP_Chi = maP_Chi;
    }

    public Timestamp getBaoCao() {
        return BaoCao;
    }

    public void setBaoCao(Timestamp BaoCao) {
        this.BaoCao = BaoCao;
    }

    

    public int getTongCong() {
        return tongCong;
    }

    public void setTongCong(int tongCong) {
        this.tongCong = tongCong;
    }
    

}
