<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%-- Vue alert 组件--%>
<script>
    Vue.component("alert", {
        template : "#alert",
        props : {
            type: {
                type: String,
                required: true
            },
            title: {
                type: String,
                required: true
            },
            msg : {
                type : String,
                required: true
            }
        }
    });
</script>
<template id="alert">
    <div class="alert alert-danger alert-dismissible">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
        <h4><i class="icon fa fa-ban"></i> {{ title }}</h4>
        {{ msg }}
    </div>
</template>
<%-- Vue alert 组件 End --%>