function getDictList(callback) {
    Vue.http.get('${ctxRest}/sys/dict/listByType/show_hide', null).then(callback);
}