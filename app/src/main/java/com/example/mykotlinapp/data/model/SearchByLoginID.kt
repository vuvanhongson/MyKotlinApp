package com.example.mykotlinapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchByLoginID (
    @SerializedName("ma_khach_hang")
    @Expose
    var ma_khach_hang: String = "",

    @SerializedName("ten_khach_hang")
    @Expose
    var ten_khach_hang: String = "",

    @SerializedName("dia_chi")
    @Expose
    var dia_chi: String = "",

    @SerializedName("ma_dich_vu")
    @Expose
    var ma_dich_vu: String = "",

    @SerializedName("gia_dich_vu")
    @Expose
    var gia_dich_vu: Int = 0,

    @SerializedName("thue")
    @Expose
    var thue: Int = 0,

    @SerializedName("tien_van_chuyen")
    @Expose
    var tien_van_chuyen: Int = 0,

    @SerializedName("tien_thu_gom")
    @Expose
    var tien_thu_gom: Int = 0,

    @SerializedName("tien_xu_ly")
    @Expose
    var tien_xu_ly: Int = 0,

    @SerializedName("cong_no")
    @Expose
    var cong_no: Int = 0,

    @SerializedName("ky_thanh_toan")
    @Expose
    var ky_thanh_toan: String = "",

    @SerializedName("hop_dong_id")
    @Expose
    var hop_dong_id: String = "",

    @SerializedName("ma_hop_dong")
    @Expose
    var ma_hop_dong: String = "",

    @SerializedName("tong_so_tien_can_thanh_toan")
    @Expose
    var tong_so_tien_can_thanh_toan: Int = 0,

    @SerializedName("trang_thai_thanh_toan")
    @Expose
    var trang_thai_thanh_toan: Int = 0,

    @SerializedName("phuong_thuc_thanh_toan")
    @Expose
    var phuong_thuc_thanh_toan: String = "",

    @SerializedName("id_thanh_toan_chi_tiet")
    @Expose
    var id_thanh_toan_chi_tiet: String = "",

    @SerializedName("ten_nhan_vien_thu_tien")
    @Expose
    var ten_nhan_vien_thu_tien: String = "",

    @SerializedName("sdt_nhan_vien_thu_tien")
    @Expose
    var sdt_nhan_vien_thu_tien: String = "",

    @SerializedName("is_phan_loai_rac_nguon")
    @Expose
    var is_phan_loai_rac_nguon: Int = 0,

    @SerializedName("text_dia_chi_dac_biet")
    @Expose
    var text_dia_chi_dac_biet: String = "",

    @SerializedName("khach_hang_id")
    @Expose
    var khach_hang_id: String = "",
): Parcelable