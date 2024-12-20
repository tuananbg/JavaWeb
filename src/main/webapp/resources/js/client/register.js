function ajaxPostTaiKhoan() {
    var data = JSON.stringify($('.taiKhoanForm').serializeJSON());
    console.log(data);

    // do post
    $.ajax({
        async: false,
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/laptopshop/api/tai-khoan/save",
        enctype: 'multipart/form-data',
        data: data,
        success: function (response) {
            if (response.status == "success") {
                $('#taiKhoanModal').modal('hide');
                alert("Thêm thành công");
            } else {
                $('input').next().remove();
                $.each(response.errorMessages, function (key, value) {
                    if (key != "id") {
                        $('input[name=' + key + ']').after('<span class="error">' + value + '</span>');
                    }
                    ;
                });
            }

        },
        error: function (e) {
            alert("Error!")
            console.log("ERROR: ", e);
        }
    });
}

$(document).on('click', '#submit', function (event) {
    event.preventDefault();
    $("#taiKhoanModal").modal();
});