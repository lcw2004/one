<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%-- Vue Confirm 组件--%>
<script>
    Vue.component("confirm", {
        template : "#confirm",
        props: {
            config: {
                type: Object,
                required: true
            }
        },
        methods: {
            okCallback : function () {
                this.config.show = false;

                if (this.config.callback) {
                    this.config.callback();
                }
            }
        }
    });
</script>
<template id="confirm">
    <div class="modal" v-show="config.show">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="config.show = false">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">确认</h4>
                </div>
                <div class="modal-body">
                    <p>{{ config.message }}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"  @click="config.show = false">取消</button>
                    <button type="button" class="btn btn-primary" @click="okCallback()">确认</button>
                </div>
            </div>
        </div>
    </div>
</template>
<%-- Vue Confirm 组件--%>