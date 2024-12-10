<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thống kê</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.3/Chart.min.js"></script>

    <script>
        window.onload = function () {
            var data = [
                {year: "2024", month: "01", total: 5000000},
                {year: "2024", month: "02", total: 6000000},
                {year: "2024", month: "03", total: 4500000},
                {year: "2024", month: "04", total: 7000000},
                {year: "2024", month: "05", total: 6500000},
                {year: "2024", month: "06", total: 8000000},
                {year: "2024", month: "07", total: 7500000},
                
            ];
            var label = [];
            var dataForDataSets = [];

            // Xử lý dữ liệu giả
            for (var i = 0; i < data.length; i++) {
                label.push(data[i].year + "-" + data[i].month);  // Ghép năm và tháng
                dataForDataSets.push(data[i].total / 1000000);  // Chuyển sang triệu đồng
            }

            // Lấy phần tử canvas để vẽ biểu đồ
            var canvas = document.getElementById('myChart');
            var chartData = {
                labels: label,
                datasets: [{
                    label: "Tổng giá trị (Triệu đồng)",
                    backgroundColor: "#0000ff",
                    borderColor: "#0000ff",
                    borderWidth: 2,
                    hoverBackgroundColor: "#0043ff",
                    hoverBorderColor: "#0043ff",
                    data: dataForDataSets,
                }]
            };

            // Các tùy chọn biểu đồ
            var options = {
                scales: {
                    yAxes: [{
                        stacked: true,
                        gridLines: {
                            display: true,
                            color: "rgba(255,99,132,0.2)"
                        }
                    }],
                    xAxes: [{
                        barPercentage: 0.5,
                        gridLines: {
                            display: false
                        }
                    }]
                },
                maintainAspectRatio: false,
                legend: {
                    labels: {
                        fontSize: 20
                    }
                }
            };

            // Vẽ biểu đồ bar
            var myBarChart = new Chart(canvas, {
                type: 'bar',
                data: chartData,
                options: options
            });
        }
    </script>

</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>
<jsp:include page="template/sidebar.jsp"></jsp:include>

<div class="col-md-10 animated bounce">
    <h3 class="page-header">Thống kê</h3>

    <canvas id="myChart" width="600px" height="400px"></canvas>
    <h4 style="text-align: center; padding-right: 10%">Biểu đồ tổng giá trị đơn hàng hoàn thành theo tháng</h4>

</div>


<jsp:include page="template/footer.jsp"></jsp:include>

<script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.3/Chart.min.js"></script>
</body>
</html>