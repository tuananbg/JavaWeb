<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Laptop Shop </title>
</head>
<body>
<jsp:include page="include/homeHeader.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <br>
            <div class="text-center mb-4">
                <img src="Frontend/img/shipping1.jpg" alt="Shipping Image 1" class="img-fluid">
                <img src="Frontend/img/shipping2.jpg" alt="Shipping Image 2" class="img-fluid">
                <img src="Frontend/img/shipping3.jpg" alt="Shipping Image 3" class="img-fluid">
            </div>

            <p><strong>1. LƯU Ý:</strong></p><br>
            <h5>- Sau khi bạn đặt hàng, trong vòng 12 giờ làm việc chúng tôi sẽ liên lạc lại để xác nhận và kiểm tra
                thông tin.</h5>
            <h5>- Những rủi ro phát sinh trong quá trình vận chuyển (va đập, ẩm ướt, tai nạn..) có thể ảnh hưởng đến
                hàng hóa, vì thế xin Quý Khách vui lòng kiểm tra hàng hóa thật kỹ trước khi ký nhận. Máy tính Hà Nội sẽ
                không chịu trách nhiệm với những sai lệch hình thức của hàng hoá sau khi Quý khách đã ký nhận hàng.</h5>
            <br>

            <p><strong>2. BẢNG GIÁ DỊCH VỤ VẨN CHUYỂN HÀNG HÓA</strong></p>
            <br>

            <table class="table table-striped mytable">
                <thead>
                <tr>
                    <th scope="col">Theo giá trị đơn hàng</th>
                    <th scope="col">Số Km được Miễn Phí</th>
                    <th scope="col">Thời gian đáp ứng</th>
                    <th scope="col">Thu phí</th>
                </tr>
                </thead>
                <tbody>
                <tr class="text-center">
                    <td>200.000đ - 500.000đ</td>
                    <td></td>
                    <td>Giao hàng trong vòng 3h</td>
                    <td>Giao hàng bán kính 15km và thu phí 20.000đ /1 lần giao. Km16 tính 6000đ/km và tối đa 45 km</td>
                </tr>
                <tr class="text-center">
                    <td>500.000đ - 1.000.000đ</td>
                    <td>20 Km</td>
                    <td>Giao hàng trong vòng 3h</td>
                    <td>Từ km thứ 21 thu phí 6.000/km, giao hàng tối đa 45 km</td>
                </tr>
                <tr class="text-center">
                    <td>1.000.000đ - 3.000.000đ</td>
                    <td>25km</td>
                    <td>Giao hàng trong vòng 3h</td>
                    <td>Từ km thứ 26 thu phí 6.000/km, giao hàng tối đa 45 km</td>
                </tr>
                <tr class="text-center">
                    <td>Trên 3.000.000đ</td>
                    <td>35km</td>
                    <td>Giao hàng trong vòng 4h, Giao hàng trong 24h (36km-45km)</td>
                    <td>Từ km thứ 36 thu phí 6.000/km, giao hàng tối đa 45 km</td>
                </tr>
                <tr class="text-center">
                    <td>10 bộ máy tính</td>
                    <td>50km</td>
                    <td>Theo thỏa thuận với khách hàng</td>
                    <td>Từ km thứ 51 thu phí 10.000/km, giao hàng tối đa 150 km</td>
                </tr>
                <tr class="text-center">
                    <td>11-20 bộ máy tính</td>
                    <td>100km</td>
                    <td>Theo thỏa thuận với khách hàng</td>
                    <td>Theo thỏa thuận với khách hàng</td>
                </tr>
                </tbody>
            </table>

            <br>
            <p>- Đối với khách hàng vượt quá khoảng cách quy định từ 46km trở lên, áp dụng phương thức gửi xe khách
                (khách hàng chọn xe để gửi) hoặc chuyển phát nhanh, khách hàng thanh toán phí vận chuyển cho bên giao
                hàng.</p>
            <br>
            <p>- Đối với đơn hàng từ 500.000 đ đến 3.000.000 đ mà cồng kềnh cần phải giao bằng ô tô thì miễn phí km như
                khoảng cách giao hàng xe máy và tính phí vượt quá km đối với ô tô là 10.000/km.</p>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>


<jsp:include page="include/homeFooter.jsp"></jsp:include>

</body>
</html>


		