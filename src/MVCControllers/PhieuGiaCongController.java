/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCControllers;

import DTO.CTP_DichVuDTO;
import DTO.CTP_GiaCongDTO;
import DTO.HangGiaCongDTO;
import DTO.P_GiaCongDTO;
import MVCModels.PhieuDichVuModel;
import MVCModels.PhieuGiaCongModel;
import MVCViews.PhieuDichVuView;
import MVCViews.PhieuHangGiaCongView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.Resource;
import table.TableData;

/**
 *
 * @author Administrator
 */
public class PhieuGiaCongController implements ActionListener{
    private PhieuHangGiaCongView mPhieuHangGiaCongView;
    private PhieuGiaCongModel mPhieuGiaCongModel;
    private String[] mHangGiaCong;
    private double dongia = 0;
    
     public PhieuGiaCongController() {
        mPhieuHangGiaCongView = new PhieuHangGiaCongView();
        mPhieuGiaCongModel = new PhieuGiaCongModel();
    }
     
      public void start()
    {
        mPhieuHangGiaCongView.setVisible(true);
        createView();
        handleEvent();
        addMoreTableRecord();

    }
    
    public void stop()
    {
        mPhieuHangGiaCongView.setVisible(false);
    }

    private void createView() {
        mHangGiaCong = new String[mPhieuGiaCongModel.getAllHangGiaCong().size()];
        
        for(int i = 0;i<mPhieuGiaCongModel.getAllHangGiaCong().size();i++)
        {
            mHangGiaCong[i] = mPhieuGiaCongModel.getAllHangGiaCong().get(i).getTenLoaiGC();
        }
        JComboBox combo = new JComboBox<String>(mHangGiaCong);
        mPhieuHangGiaCongView.getmTable().getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(combo));
        mPhieuHangGiaCongView.getmTable().putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        
        combo.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String tenGiaCong = (String)cb.getSelectedItem();
                
                
                
                for(int i = 0;i<mPhieuGiaCongModel.getAllHangGiaCong().size();i++)
                {
                    if(mPhieuGiaCongModel.getAllHangGiaCong().get(i).getTenLoaiGC().equals(tenGiaCong))
                    {
                        dongia = mPhieuGiaCongModel.getAllHangGiaCong().get(i).getDonGia();
                        break;
                    }
                }
                int selectedRow = mPhieuHangGiaCongView.getmTable().getSelectedRow();
                if(selectedRow>-1)
                {
                     mPhieuHangGiaCongView.getmTableModel().setValueAt(dongia+"", selectedRow, 2);
                     if(mPhieuHangGiaCongView.getmTableModel().getValueAt(selectedRow, 1).toString().trim().length()!=0)
                        mPhieuHangGiaCongView.getmTableModel().setValueAt(dongia*Integer.valueOf(mPhieuHangGiaCongView.getmTableModel().getmObjectList().get(selectedRow).getDataAt(1))+"", selectedRow, 3);
                     
                }
                
                   
                
            }
        });
        
        JTextField textField = new JTextField();
        mPhieuHangGiaCongView.getmTable().getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(textField));
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
              warn();
            }
            public void removeUpdate(DocumentEvent e) {
              warn();
            }
            public void insertUpdate(DocumentEvent e) {
              warn();
            }

            public void warn() {
                if(textField.getText().toString().trim().length()>0)
                {
                    if(isInteger(textField.getText().toString().trim()))
                        mPhieuHangGiaCongView.getmTableModel().setValueAt(dongia*Integer.parseInt(textField.getText().toString().trim())+"", mPhieuHangGiaCongView.getmTable().getSelectedRow(), 3);
                    else
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập số");
                }
               
            }
        });
        
        mPhieuHangGiaCongView.getTextMaPhieu().setText(mPhieuGiaCongModel.getNextIdOfPhieuGiaCong());
        mPhieuHangGiaCongView.getTextMaPhieu().setEditable(false);
        mPhieuHangGiaCongView.getDateNgayNhanHang().setDate(getCurrentDate());
        mPhieuHangGiaCongView.getTextMaKH().setEditable(false);
        mPhieuHangGiaCongView.getTextDiaChi().setEditable(false);
        
    }
    
    
    
    public Date getCurrentDate()
    {
	   Calendar cal = Calendar.getInstance();
	   return cal.getTime();
    }

    private void handleEvent() {
        mPhieuHangGiaCongView.getmClearButton().addActionListener(this);
        mPhieuHangGiaCongView.getmExitButton().addActionListener(this);
        mPhieuHangGiaCongView.getmSaveButton().addActionListener(this);
        mPhieuHangGiaCongView.getmAddButton().addActionListener(this);
        mPhieuHangGiaCongView.getmRemoveButton().addActionListener(this);
        mPhieuHangGiaCongView.getTextHoTen().addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                String tenGiaCong = (String)cb.getSelectedItem();
                int indexOfThoGiaCong = cb.getSelectedIndex();
                
                mPhieuHangGiaCongView.getTextMaKH().setText(mPhieuHangGiaCongView.getmAllThoGiaCong().get(indexOfThoGiaCong).getMaTho());
                mPhieuHangGiaCongView.getTextDiaChi().setText(mPhieuHangGiaCongView.getmAllNguoi().get(indexOfThoGiaCong).getDiaChi());
            }
        });
    }
    

    private void addMoreTableRecord() {
        int size = mPhieuHangGiaCongView.getmTableModel().getmObjectList().size();
        
        if((size>0 && mPhieuHangGiaCongView.getmTableModel().getValueAt(size-1, 3).toString().trim().length()!=0)|| size == 0)
        {
            ArrayList<String> data = new ArrayList<>();
            data.add(mPhieuGiaCongModel.getAllHangGiaCong().get(0).getTenLoaiGC());
            data.add("");
            dongia = mPhieuGiaCongModel.getAllHangGiaCong().get(0).getDonGia();
            data.add(dongia+"");
            data.add("");
            TableData temp = new TableData(data);
            mPhieuHangGiaCongView.getmTableModel().addObject(temp);
        }else
        {
            JOptionPane.showMessageDialog(null,"Vui lòng nhập đầy đủ thông tin");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand())
        {

            case Resource.CLEAR:
                clearData();
                break;
            case Resource.EXIT:
                stop();
                break;
            case Resource.SAVE:
                saveData();
                break;
            case Resource.ADD:
                addMoreTableRecord();
                break;
            case Resource.REMOVE:
                removeTableRecord();
                break;
        }
    }

    private void removeTableRecord() {
        if(mPhieuHangGiaCongView.getmTable().getSelectedRow()>=0)
            mPhieuHangGiaCongView.getmTableModel().removeObject(mPhieuHangGiaCongView.getmTable().getSelectedRow());
        else
            JOptionPane.showMessageDialog(null,"Vui lòng chọn 1 dòng để xóa");
    }

    private void saveData() {
         if(this.isTableEmpty()!=true && mPhieuHangGiaCongView.isAllTextFilled()==true)
        {
            double finalMoney = calculateFinalMoney();
            mPhieuHangGiaCongView.getTextTongCong().setText(finalMoney+"");
            
            String nextIdOfPhieuGiaCong = mPhieuHangGiaCongView.getTextMaPhieu().getText().toString();
            P_GiaCongDTO pGiaCong = new P_GiaCongDTO(nextIdOfPhieuGiaCong,mPhieuHangGiaCongView.getTextMaKH().getText().toString().trim(), 
                    new Timestamp(mPhieuHangGiaCongView.getDateNgayNhanHang().getDate().getTime()), 
                    new Timestamp(mPhieuHangGiaCongView.getDateNgayThanhToan().getDate().getTime()), finalMoney);
            
            mPhieuGiaCongModel.insertP_GiaCong(pGiaCong);
            
            for(TableData data : mPhieuHangGiaCongView.getmTableModel().getmObjectList())
            {
                String tenDv = data.getDataAt(0);
                String idGc = "";
                for(HangGiaCongDTO giacongDTO : mPhieuGiaCongModel.getAllHangGiaCong())
                {
                    if(tenDv.equals(giacongDTO.getTenLoaiGC()))
                    {
                        idGc = giacongDTO.getMaLoaiGC();
                        break;
                    }
                }
                
                CTP_GiaCongDTO ctp = new CTP_GiaCongDTO(mPhieuGiaCongModel.getNextIdOfCTPGC(),
                        idGc,
                        nextIdOfPhieuGiaCong,
                        Integer.parseInt(data.getDataAt(1)),Double.parseDouble(data.getDataAt(3)));
                mPhieuGiaCongModel.insertCTP_GiaCong(ctp);
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin");
        }                   
    }
    
    private double calculateFinalMoney()
    {
        double sum = 0;
        for(int i = 0;i<mPhieuHangGiaCongView.getmTable().getRowCount();i++)
        {
            sum+= Double.parseDouble((String) mPhieuHangGiaCongView.getmTableModel().getValueAt(i, 3));
        }
        return sum;
    }
    
    private boolean isTableEmpty()
    {
        for(int i = 0;i<mPhieuHangGiaCongView.getmTable().getRowCount();i++)
        {
            if(mPhieuHangGiaCongView.getmTableModel().getValueAt(i, 3).toString().trim().length()==0)
                return true;
        }
        return false;
    }

    private void clearData() {
        mPhieuHangGiaCongView.getTextDiaChi().setText("");
        mPhieuHangGiaCongView.getTextMaKH().setText("");
        mPhieuHangGiaCongView.getTextTongCong().setText("");
        mPhieuHangGiaCongView.getDateNgayNhanHang().cleanup();
        mPhieuHangGiaCongView.getDateNgayThanhToan().cleanup();
    }
    
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(Exception e) { 
            return false; 
        }
    // only got here if we didn't return false
        return true;
    }
}