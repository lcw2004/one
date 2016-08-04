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
            },
            pageNo: {
                type: Number,
                required: true
            }
        },
        methods : {
            goToPage : function (pageNum) {
                this.pageNo = pageNum;
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
    <nav>
        <ul class="pagination">
            <li>
                <a @click="goToPage(lastPageNo)">上一页</a>
            </li>
            <li v-for="pageNo of showPageNos" :class="{'active': pageNo ==page.pageNo}">
                <a @click="goToPage(pageNo)">{{ pageNo }}</a>
            </li>
            <li>
                <a @click="goToPage(nextPageNo)">下一页</a>
            </li>
        </ul>
    </nav>
</template>
<%-- Vue 分页组件 End --%>