/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import DTO.DichVuDTO;
import DTO.NguoiDTO;
import DTO.P_GiaCongDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class P_GiaCongDAO {
    CallableStatement call = null;
    Connection connection = null;
    
    //Properties of table
    private static final String maPhieuGiaCong = "MAP_GC";
    private static final String maThoGiaCong = "MATHOGC";
    private static final String ngayNhanHang = "NGAYNHANHANG";
    private static final String ngayThanhToan = "NGAYTHANHTOAN";
    private static final String tongCong = "TONGCONG";
    
    private static final String insertStatement = "{call P_GIACONG_Ins(?,?,?,?,?)}";
    private static final String updateStatement = "{call P_GIACONG_Upd(?,?,?,?,?)}";
    private static final String deleteStatement = "{call P_GIACONG_Del(?)}";
    
    //TAG
    private static final String TAG = P_GiaCongDAO.class.getSimpleName();
    
    
    public P_GiaCongDAO(){
        
    }
    
    /*
    * CRUD
    */
    public boolean insert(P_GiaCongDTO n){
        //create procedure DICHVU_Ins (MADV varchar(10), TENDV varchar(30) )
        try {
            connection = DataSource.getInstance().getConnection();
            call = connection.prepareCall(insertStatement);
            
            call.setString(maPhieuGiaCong, n.getMaP_GC());
            call.setString(maThoGiaCong, n.getMaThoGC());
            call.setTimestamp(ngayNhanHang, n.getNgayNhanHang());
            call.setTimestamp(ngayThanhToan, n.getNgayThanhToan());
            call.setDouble(tongCong,n.getTongCong());
            
            return call.execute();
                    
            
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
        finally{
            if(connection!= null)
                try {
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public boolean update(P_GiaCongDTO n){
        try {
            // procedure DICHVU_Upd (MADV varchar(10), TENDV varchar(30) )
            connection = DataSource.getInstance().getConnection();
            
            call = connection.prepareCall(updateStatement);
            call.setString(maPhieuGiaCong, n.getMaP_GC());
            call.setString(maThoGiaCong, n.getMaThoGC());
            call.setTimestamp(ngayNhanHang, n.getNgayNhanHang());
            call.setTimestamp(ngayThanhToan, n.getNgayThanhToan());
            call.setDouble(tongCong,n.getTongCong());
            
            return call.execute();
           
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
        finally{
            if(connection!= null)
                try {
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public boolean delete(P_GiaCongDTO n){
        try {
            //create procedure DICHVU_Del (MADV varchar(10) )

            connection = DataSource.getInstance().getConnection();
            call = connection.prepareCall(deleteStatement);
            call.setString(maPhieuGiaCong, n.getMaP_GC());
            
            return call.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
        }
        finally{
            if(connection!=null)
                try {
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(TAG).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}