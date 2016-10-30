<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

<%--区域树模态窗--%>
<script>
    Vue.component("area-tree-modal", {
        mixins: [TreeModalMixin],
        mounted: function () {
            var actions = {getMenuTree: {method: "get", url: '${ctxRest}/sys/menu/tree'}};
            var resource = this.$resource(null, {}, actions);
            resource.getMenuTree().then(function (response) {
                this.menu = response.body;
            });
        }
    });
</script>