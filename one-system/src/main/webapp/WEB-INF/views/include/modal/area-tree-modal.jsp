<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

<%--区域树模态窗--%>
<script>
    Vue.component("area-tree-modal", {
        mixins: [TreeModalMixin],
        mounted: function () {
            var actions = {getTree: {method: "get", url: '${ctxRest}/sys/area/tree'}};
            var resource = this.$resource(null, {}, actions);
            resource.getTree().then(function (response) {
                this.topElement = response.body;
            });
        }
    });
</script>