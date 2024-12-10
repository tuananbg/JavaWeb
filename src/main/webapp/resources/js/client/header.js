$(document).ready(function() {
	ajaxGet2(); // Gọi hàm AJAX khi tài liệu đã sẵn sàng

	function ajaxGet2() {
		$.ajax({
			type: "GET",
			url: "http://localhost:8080/laptopshop/api/danh-muc/allForReal",
			success: function(result) {
				$.each(result, function(i, danhmuc) {
					// Tạo nội dung cho từng danh mục
					var content = '<li><a href="/laptopshop/store?brand=' + encodeURIComponent(danhmuc.tenDanhMuc) + '">' +
						'<span style="font-family:sans-serif;color: #333;font-size: 12px;">' +
						danhmuc.tenDanhMuc + '</span></a></li>';
					$('#danhmuc').append(content); // Thêm danh mục vào danh sách
				});

				// Gọi hàm để thêm lớp active cho các liên kết
				addActiveClass();
			},
			error: function(xhr, status, error) {
				console.error("Có lỗi xảy ra khi lấy dữ liệu: ", error); // Hiển thị lỗi nếu có
			}
		});
	}

	// Hàm để thêm lớp active
	function addActiveClass() {
		// Lấy tất cả các liên kết trong thanh điều hướng
		const navLinks = document.querySelectorAll('.nav-link');

		// Lấy URL hiện tại
		const currentURL = window.location.pathname;

		// Duyệt qua tất cả các liên kết
		navLinks.forEach(link => {
			// Lấy đường dẫn của liên kết
			const linkURL = link.getAttribute('href');

			// Kiểm tra nếu đường dẫn của liên kết khớp với URL hiện tại
			if (linkURL === currentURL) {
				link.classList.add('active'); // Thêm lớp active
			}
		});
	}

	// Khởi động slideshow
	$("#slider1").responsiveSlides({
		maxwidth: 1600,
		speed: 600
	});
});
