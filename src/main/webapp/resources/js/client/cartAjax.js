function changeQuanity(id, value, price) {
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/laptopshop/api/gio-hang/changSanPhamQuanity",
		data: { id: id, value: value },
		success: function() {
			updateItemTotal(id, value, price);
			updateOrderTotal();
			console.log("Số lượng đã được cập nhật thành công");
		},
		error: function(e) {
			alert("Lỗi: " + e);
			console.error("Error", e);
		}
	});
}

function deleteFromCart(id) {
	// Hiển thị hộp thoại xác nhận trước khi xóa
	if (confirm("Bạn có chắc chắn muốn xóa sản phẩm này khỏi giỏ hàng?")) {
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/laptopshop/api/gio-hang/deleteFromCart",
			data: { id: id },
			success: function() {
				$("#item" + id).remove();
				updateOrderTotal();
				alert("Sản phẩm đã được xóa khỏi giỏ hàng."); // Thông báo thành công
			},
			error: function(e) {
				alert("Lỗi: " + e);
				console.error("Error", e);
			}
		});
	} else {
		console.log("Xóa sản phẩm bị hủy bỏ.");
	}
}


function updateItemTotal(id, quantity, price) {
	let total = quantity * price;
	$("#item" + id + "_total").text(formatCurrency(total));
}

function updateOrderTotal() {
	let total = 0;
	$(".total").each(function() {
		total += parseInt($(this).text().replace(/\D/g, ''));
	});
	$("#ordertotal").text(formatCurrency(total));
}

function formatCurrency(amount) {
	return amount.toLocaleString("vi-VN") + " VND";
}
