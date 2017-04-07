<%@ page contentType="text/html;charset=UTF-8" %><meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

<%-- Vue 分页组件 Start --%>
<script>
    var offset = 2;
    Vue.component('pagination', {
        template: '#pagination',
        props: {
            page: {
                type: Object,
                required: true
            }
        },
        data : function(){
            return {
                pageNo: 1,
                pageSize: 10
            }
        },
        watch: {
            pageNo: {
                handler: function () {
                    this.$emit("page-no", this.pageNo);
                }
            },
            pageSize: {
                handler: function () {
                    this.$emit("page-size", this.pageSize);
                }
            }
        },
        computed: {
            lastPageNo: function () {
                // 上一页
                if(this.page.pageNo == 1) {
                    return 1;
                } else {
                    return this.page.pageNo - 1;
                }
            },
            nextPageNo: function () {
                // 下一页
                if(this.page.pageNo < this.page.pageCount) {
                    return this.page.pageNo + 1;
                } else {
                    return this.page.pageCount;
                }
            },
            showPageNos: function () {
                var from = this.page.pageNo - offset;
                if(from < 1) {
                    from = 1;
                }

                var to = from + (offset * 2);
                if(to >= this.page.pageCount) {
                    to = this.page.pageCount;
                }

                var showPageNos = new Array();
                for(var i = from; i <= to; i++) {
                    showPageNos.push(i);
                }
                return showPageNos;
            }
        }
    });
</script>
<template id="pagination">
    <div class="row">
        <div class="col-sm-6">
            <ul class="pagination">
                <li>
                    <a @click="pageNo = lastPageNo">上一页</a>
                </li>
                <li v-for="num of showPageNos" :class="{'active': num == page.pageNo}">
                    <a @click="pageNo = num">{{ num }}</a>
                </li>
                <li>
                    <a @click="pageNo = nextPageNo">下一页</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-6">
            <ul class="pagination  pull-right navbar-static-top">
                <li>
                    共 {{ page.count }} 条，每页
                    <div class="btn-group dropup">
                        <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown">{{ pageSize }}<span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a @click="pageSize = 10">10</a></li>
                            <li><a @click="pageSize = 25">25</a></li>
                            <li><a @click="pageSize = 50">50</a></li>
                            <li><a @click="pageSize = 100">100</a></li>
                        </ul>
                    </div>
                    条
                </li>
            </ul>
        </div>
    </div>
</template>
<%-- Vue 分页组件 End --%>