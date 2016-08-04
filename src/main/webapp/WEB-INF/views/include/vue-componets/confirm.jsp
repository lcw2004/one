<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%--<modal is-show="true" msg="123123123" callback="okCallback"></modal>--%>

<%-- Vue Confirm 组件--%>
<script>
    Vue.component("modal", {
        template : "#modal",
        props: {
            msg : {
                type : String,
                require : true
            },
            callback : {
                type: Function,
                require : true
            }
        },
        data : function () {
            return {
                isShow : true
            }
        },
        methods : {
            hide : function () {
                this.isShow = false
            },
            okCallBack : function () {
                this.callback();
            }
        }
    });
</script>
<template id="modal">
    {{ msg }}
    <div class="modal modal-primary" v-if="isShow == true" style="display: block">1111
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" @click="hide()">
                        <span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">确认信息</h4>
                </div>
                <div class="modal-body">
                    <p>{{ msg }}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline" data-dismiss="modal"  @click="hide()">取消</button>
                    <button type="button" class="btn btn-outline" @click="">确认</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
</template>