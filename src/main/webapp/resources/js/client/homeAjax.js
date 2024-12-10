function addToCart(id) {
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/laptopshop/api/gio-hang/addSanPham?id=" + id,
		success: function (result) {
			if (result.status == "false") {
				window.alert("Sản phẩm đang hết hàng, quý khách vui lòng quay lại sau");
			} else {
				window.alert("Đã thêm sản phẩm vào giỏ hàng");
			}
		},
		error: function (e) {
			alert("Error: ", e);
			console.log("Error", e);
		}
	});
}

$(document).ready(function () {
	ajaxGet();

	function ajaxGet() {
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/laptopshop/api/san-pham/latest",
			success: function (result) {
				var content;
				var section = '<div class="row">';
				var endsection = '</div><br>';
				$.each(result, function (i, sanpham) {
					if (i % 4 == 0) content = '';

					content += '<div class="col-md-3 col-sm-6 mb-4">' +
						'<div class="card h-100 text-center">' +
						'<a href="sp?id=' + sanpham.id + '">' +
						'<img class="card-img-top" style="width: 100%; height: 238px;" src="/laptopshop/img/' + sanpham.id + '.png" alt="' + sanpham.tenSanPham + '">' +
						'</a>' +
						'<div class="card-body">' +
						'<h5 class="card-title">' + sanpham.tenSanPham + '</h5>' +
						'<h6 class="text-muted card-price">' + accounting.formatMoney(sanpham.donGia) + ' VNĐ</h6>' + // Sử dụng lớp card-price
						'<button onClick="addToCart(' + sanpham.id + ')" class="btn btn-warning btn-add-to-cart">' + // Thêm lớp btn-add-to-cart
						'<i class="bi bi-cart-plus"></i>Thêm vào Giỏ hàng</button>' +
						'</div>' +
						'</div>' +
						'</div>';


					if (i % 4 == 3 || i == result.length - 1) {
						$('.content-grids').append(section + content + endsection);
					}
				});
			},
			error: function (e) {
				alert("Error: ", e);
				console.log("Error", e);
			}
		});
	}
});
