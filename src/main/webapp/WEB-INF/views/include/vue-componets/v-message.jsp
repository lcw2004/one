<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<div id="VMessage">
    <alert :config="alertConfig"></alert>
</div>
<script>
    var VMessage = {};
    var vueMessage = new Vue({
        el: '#VMessage',
        data: {
            alertConfig: {
                isShow: false,
                message: ""
            }
        }
    });

    VMessage.alert = function (message) {
        vueMessage.alertConfig.message = message;
        vueMessage.alertConfig.isShow = true;
    };

    Vue.$alert = VMessage.alert;
</script>