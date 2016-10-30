<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

<%--机构树模态窗--%>
<script>
    Vue.component("office-tree-modal", {
        mixins: [TreeModalMixin],
        mounted: function () {
            var actions = {getMenuTree: {method: "get", url: '${ctxRest}/sys/office/tree'}};
            var resource = this.$resource(null, {}, actions);
            resource.getMenuTree().then(function (response) {
                this.menu = response.body;
            });
        }
    });
</script>