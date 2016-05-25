/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAcessLayer;

import DTO.DichVuDTO;
import DTO.NguoiDTO;
import DTO.TonKhoDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class TonKhoDAO {
    CallableStatement call = null;
    Connection connection = null;
    
    //Properties of table
    private static final String maTonKho = "MAP_TK";
    private static final String ngayBaoCao = "NGAYBAOCAO";
    
    private static final String insertStatement = "{call TONKHO_Ins(?,?)}";
    private static final String updateStatement = "{call TONKHO_Upd(?,?,?,?)}";
    private static final String deleteStatement = "{call TONKHO_Del(?)}";
    
    //TAG
    private static final String TAG = TonKhoDAO.class.getSimpleName();
    
    
    public TonKhoDAO(){
        
    }
    
    /*
    * CRUD
    */
    public boolean insert(TonKhoDTO n){
        //create procedure DICHVU_Ins (MADV varchar(10), TENDV varchar(30) )
        try {
            connection = DataSource.getInstance().getConnection();
            call = connection.prepareCall(insertStatement);
            
            call.setString(maTonKho, n.getMaP_TK());
            call.setTimestamp(ngayBaoCao, n.getNgayBaoCao());
            
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
    
    public boolean update(TonKhoDTO n){
        try {
            // procedure DICHVU_Upd (MADV varchar(10), TENDV varchar(30) )
            connection = DataSource.getInstance().getConnection();
            
            call = connection.prepareCall(updateStatement);
            call.setString(maTonKho, n.getMaP_TK());
            call.setTimestamp(ngayBaoCao, n.getNgayBaoCao());
            
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
    
    public boolean delete(TonKhoDTO n){
        try {
            //create procedure DICHVU_Del (MADV varchar(10) )

            connection = DataSource.getInstance().getConnection();
            call = connection.prepareCall(deleteStatement);
            call.setString(maTonKho, n.getMaP_TK());
            
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