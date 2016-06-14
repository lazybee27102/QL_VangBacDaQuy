package MVCModels;

import java.awt.color.ICC_ColorSpace;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import com.mchange.v1.xmlprops.SaxXmlPropsParser;
import com.toedter.calendar.JDateChooser;

import DTO.CTP_BanHangDTO;
import DTO.KhachHangDTO;
import DTO.NguoiDTO;
import DTO.P_BanHangDTO;
import DTO.P_ThuDTO;
import DTO.SanPhamDTO;
import DataAcessLayer.CTP_BanHangDAO;
import DataAcessLayer.KhachHangDAO;
import DataAcessLayer.NguoiDAO;
import DataAcessLayer.P_BanHangDAO;
import DataAcessLayer.P_ThuDAO;
import DataAcessLayer.SanPhamDAO;

public class PhieuBanHangModel {
	
	private NguoiDAO nguoiDAO;
	private KhachHangDAO khachHangDAO;
	private P_ThuDAO p_ThuDAO;
	private P_BanHangDAO p_BanHangDAO;
	private CTP_BanHangDAO ctp_BanHangDAO;
	private SanPhamDAO sanPhamDAO;
	
	public PhieuBanHangModel() {
		// TODO Auto-generated constructor stub
		nguoiDAO = new NguoiDAO();
		khachHangDAO = new KhachHangDAO();
		p_ThuDAO = new P_ThuDAO();
		p_BanHangDAO = new P_BanHangDAO();
		ctp_BanHangDAO = new CTP_BanHangDAO();
		sanPhamDAO = new SanPhamDAO();
	}
	
	public String getNextIdOfNguoi()
	{
		return nguoiDAO.getNexId();
	}
	
	public String getNextIdOfKhachHang()
	{
		return khachHangDAO.getNexId();
	}
	
	public String getNextIdOfPhieuThu()
	{
		return p_ThuDAO.getNexId();
	}
	
	public String getNextIdOfPhieuBanHang()
	{
		return p_BanHangDAO.getNexId();
	}
	
	public String getNextIdOfChiTietPhieuBanHang()
	{
		return ctp_BanHangDAO.getNexId();
	}
	
	public NguoiDTO kiemTraKhachQuen( int shortId )
	{
		return khachHangDAO.checkKhachHangQuen(shortId);
	}
	
	public ArrayList<SanPhamDTO> getAllSanPham()
	{	
		return sanPhamDAO.getAllSanPham();
	}
	
	public boolean updateSoLuongTonOfSanPham(int updateSoLuongTon, String maSP)
	{
		return sanPhamDAO.updateSLTSanPham(updateSoLuongTon, maSP);
	}
	
	public void insertKhachHang( NguoiDTO nguoiDTO, KhachHangDTO khachHangDTO )
	{
		nguoiDAO.insert(nguoiDTO);
		khachHangDAO.insert(khachHangDTO);
	}
	
	public void insertPhieuThu( P_ThuDTO p_ThuDTO )
	{
		p_ThuDAO.insert(p_ThuDTO);
	}
	
	public void insertPhieuBanHang( P_BanHangDTO p_BanHangDTO )
	{
		p_BanHangDAO.insert(p_BanHangDTO);
	}
	
	public void insertChiTietPhieuBanHang( CTP_BanHangDTO ctp_BanHangDTO )
	{
		ctp_BanHangDAO.insert(ctp_BanHangDTO);
	}
	
	public String getMaKhById( int id )
	{
		return khachHangDAO.getMaKhachHangByName(id);
	}
	
	
}
