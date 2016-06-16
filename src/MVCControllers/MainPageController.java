/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCControllers;

import MVCViews.MainPageView;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 *
 * @author Minh Nhat
 */
public class MainPageController implements Controller {

    private MainPageView view = null;
    private Controller parent;

    public void start() {
        view = new MainPageView(this);
        view.getFrame().setVisible(true);
    }

    public void btnBanHangActionPerformed(ActionEvent e) {
        PhieuBanHangController controller = new PhieuBanHangController();
        controller.setParent(this);
        controller.start();
    }

    public void btnMuaHangActionPerformed(ActionEvent e) {
        PhieuMuaHangController controller = new PhieuMuaHangController();
        controller.setParent(this);
        controller.start();
    }

    public void btnDichVuActionPerformed(ActionEvent e) {
        PhieuDichVuController controller = new PhieuDichVuController();
        controller.setParent(this);
        controller.start();
    }

    public void btnChiActionPerfomed(ActionEvent e) {
        PhieuChiController controller = new PhieuChiController();
        controller.setParent(this);
        controller.start();
    }

    public void btnGiaCongActionPerfomed(ActionEvent e) {
        PhieuGiaCongController controller = new PhieuGiaCongController();
        controller.setParent(this);
        controller.start();

    }

    public void btnNoActionPerformed(ActionEvent e) {
        PhieuNoController controller = new PhieuNoController();
        controller.setParent(this);
        controller.start();
    }

    public void btnTonKhoActionPerformed(ActionEvent e) {
        TonKhoController controller = new TonKhoController();
        controller.setParent(this);
        controller.start();
    }

    public void btnThoatActionPerformed(ActionEvent e) {
        view.getFrame().dispatchEvent(new WindowEvent(view.getFrame(), WindowEvent.WINDOW_CLOSING));
    }

    public MainPageController() {
    }

    @Override
    public void setParent(Controller parent) {
        this.parent = null;
    }

    @Override
    public void setParentVisiableFalse() {
        this.view.getFrame().setVisible(false);
    }

    @Override
    public void setParentVisiableTrue() {
        this.view.getFrame().setVisible(true);
    }

}
