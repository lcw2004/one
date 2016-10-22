<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%-- Vue alert 组件--%>
<template id="alert" >
    <div class="modal" v-show="config.show">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true" @click="config.show = false">×</span></button>
                    <h4 class="modal-title">提示</h4>
                </div>
                <div class="modal-body">
                    <p>{{ config.message }}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" @click="okCallback()">确定</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    Vue.component("alert", {
        template: "#alert",
        props: {
            config: {
                type: Object,
                required: true
            }
        },
        methods: {
            okCallback : function () {
                this.config.show = false;
                this.config.callback();
            }
        }
    });
</script>
<%-- Vue alert 组件 End --%>