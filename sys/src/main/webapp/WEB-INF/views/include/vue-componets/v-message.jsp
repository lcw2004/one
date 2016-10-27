<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<div id="VMessage">
    <alert :config="alertConfig"></alert>
    <confirm :config="confirmConfig"></confirm>
</div>
<script>
    var VMessage = {};
    var vueMessage = new Vue({
        el: '#VMessage',
        data: {
            alertConfig: {
                show: false,
                message: String,
                callback: Function
            },
            confirmConfig: {
                show: false,
                message: String,
                callback: Function
            }
        }
    });

    VMessage.alert = function (message, callback) {
        vueMessage.alertConfig.message = message;
        vueMessage.alertConfig.show = true;
        vueMessage.alertConfig.callback = callback;
    };
    VMessage.confirm = function (message, callback) {
        vueMessage.confirmConfig.message = message;
        vueMessage.confirmConfig.show = true;
        vueMessage.confirmConfig.callback = callback;
    };

    Vue.$alert = VMessage.alert;
    Vue.$confirm = VMessage.confirm;
</script>