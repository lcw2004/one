<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script>
    Vue.filter('prettyJson', function (value) {
        var jsonStr = JSON.stringify(value, undefined, 4);
        return jsonStr;
    });

    Vue.filter('fillSpace', function (value) {
        var space = "";
        for (var i = 0;i < value; i++) {
            space += "&nbsp;&nbsp;&nbsp;&nbsp;";
        }
        return space;
    });
</script>