<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

<%--菜单树模态窗--%>
<script>
    Vue.component("menu-tree-modal", {
        mixins: [TreeModalMixin],
        mounted: function () {
            var actions = {getTree: {method: "get", url: '${ctxRest}/sys/menu/tree'}};
            var resource = this.$resource(null, {}, actions);
            resource.getTree().then(function (response) {
                this.topElement = response.body;
            });
        }
    });
</script>
