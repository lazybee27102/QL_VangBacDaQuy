/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCControllers;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import DTO.CT_TonKhoDTO;
import DTO.TonKhoDTO;
import MVCModels.TonKhoModel;
import MVCViews.TonKhoView;

/**
 *
 * @author Minh Nhat
 */
public class TonKhoController implements Controller{

    TonKhoModel model = new TonKhoModel();
    TonKhoView view = null;
    private Controller parent;

    /*
     Khi vừa  load form, thì tất cả các thông tin đều đã được hiện thị lên.
     Gồm : Mã phiếu tồn kho, ngày báo cáo. 
     */
    public void start() {
        view = new TonKhoView(this);
        view.getFrame().setVisible(true);
        setParentVisiableFalse();
    }

    /*
     1. Intent
     - Lưu bảng tồn kho 
     - lưu chi tiết tồn kho
     */
    public void btnLuuActionPerformed(ActionEvent e) {
        TonKhoDTO tkDTO = new TonKhoDTO();
        tkDTO.setMaP_TK(Integer.parseInt(view.getTextMaPhieu().getText()));
        tkDTO.setNgayBaoCao(new Timestamp(view.getDateNgayBaoCao().getDate().getTime()));

        model.insertTonKho(tkDTO);
        List<CT_TonKhoDTO> lCT_TK = view.getTableModel().getCT_TonKhoDTOList();
        for (int i = 0; i < lCT_TK.size(); i++) {
            lCT_TK.get(i).setMaCTP_TK(model.getNextIDOfCT_TK());
            lCT_TK.get(i).setMaP_TK(tkDTO.getMaP_TK());
            model.insertCT_TonKho(lCT_TK.get(i));
        }

        JOptionPane.showMessageDialog(null, "Thêm thành công");
        view.getBtnLuu().setEnabled(false);
    }

    public void btnThoatActionEvent(ActionEvent e) {
        //view.getFrame().dispatchEvent(new WindowEvent(view.getFrame(), WindowEvent.WINDOW_CLOSING));
        view.getFrame().setVisible(false);
        setParentVisiableTrue();
    }

    public void frameWindowOpened(WindowEvent e) {
        view.getDateNgayBaoCao().setDate(new Date());
        model.init(new Timestamp(view.getDateNgayBaoCao().getDate().getTime()));
        view.getTextMaPhieu().setText(String.valueOf(model.getNextID()));

        fillDataToTableModel();

    }

    /*
     1. điền dữ liệu vào lsCTP_TK
     2. load dữ liệu này lên bảng
     */
    private void fillDataToTableModel() {
        int maSP;
        int tonDauKy, soLuongBan, soLuongMua;
        CT_TonKhoDTO tct_tk = null;
        for (int i = 0; i < model.getListMaSP().size(); i++) {
            maSP = model.getListMaSP().get(i);
            tonDauKy = model.getMaSPTonDauKy().get(maSP);
            soLuongBan = model.getMaSPTongBan().get(maSP);
            soLuongMua = model.getMaSPTongMua().get(maSP);

            tct_tk = new CT_TonKhoDTO();
            tct_tk.setMaHang(maSP);
            tct_tk.setTonDauKy(tonDauKy);
            tct_tk.setSoLuongBan(soLuongBan);
            tct_tk.setSoLuongMua(soLuongMua);
            tct_tk.setTonCuoiKy(tonDauKy + soLuongMua - soLuongBan);

            view.getTableModel().addRow(tct_tk);
        }
    }

     @Override
    public void setParent(Controller parent) {
        this.parent = parent;
        parent.addChild(this);
    }

    @Override
    public void setParentVisiableFalse() {
        this.parent.setParentVisiableFalse();
    }

   @Override
    public void setParentVisiableTrue() {
        this.parent.setParentVisiableTrue();
    }

    @Override
    public void addChild(Controller child) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void dipose() {
        view.getFrame().dispatchEvent(new WindowEvent(view.getFrame(), WindowEvent.WINDOW_CLOSING));
    }
}
