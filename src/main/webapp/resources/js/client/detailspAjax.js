$(document).ready(function(){
	$(".add-to-cart").click(function(){
		ajaxGet($("#spid").text());
	});

	function ajaxGet(id){
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/laptopshop/api/gio-hang/addSanPham?id="+id,
			success: function(result){
				if(result.status == "false") {
					showNotification("Sản phẩm đang hết hàng, quý khách vui lòng quay lại sau", "error");
				} else {
					showNotification("Đã thêm sản phẩm vào giỏ hàng", "success");
				}
			},
			error : function(e){
				showNotification("Đã xảy ra lỗi. Vui lòng thử lại sau.", "error");
				console.log("Error", e);
			}
		});
	}

	function showNotification(message, type) {
		var notification = $("#notification");
		notification.text(message);
		notification.css("background-color", type === "success" ? "#82c5e1" : "#cb6f77"); // Xanh cho thành công, đỏ cho lỗi
		notification.fadeIn(300).delay(2000).fadeOut(300); // Hiện thông báo, giữ trong 2 giây, rồi ẩn
	}
});
