/**
 * 递归初始化菜单列表，将menu下的子菜单添加tempList中
 * */
    function initMenu(menu, level, tempList) {
    var childList = menu.childList;
    if (!childList) {
        return;
    }
    for (var i = 0; i < childList.length; i++) {
        var childMenu = childList[i];
        childMenu.level = level;
        Vue.set(childMenu, "isShow", level <= 3);// 是否显示，1,2,3级菜单默认显示
        Vue.set(childMenu, "isExpanded", level <= 2);// 是否展开菜单，1,2,3级菜单默认展开
        tempList.push(childMenu);

        initMenu(childMenu, level + 1, tempList);
    }
}

/**
 * 切换下一级菜单的状态，主要用于打开，打开的时候只显示下一级，且只修改本级的显示状态
 * @param menu
 * @param isShow
 */
function toggleChildMenuList(menu, isShow) {
    // 修改展开状态
    menu.isExpanded = isShow;

    // 修改子节点的现实状态
    var list = menu.childList;
    if (list == null) {
        return;
    }
    for (var i = 0; i < list.length; i++) {
        var childMenu = list[i];
        childMenu.isShow = isShow;
    }
}

/**
 * 递归切换所有菜单的状态，主要用于关闭，关闭的时候需要修改所有子节点的展开及显示状态
 * @param menu
 * @param isShow
 */
function toggleChildMenuListRecursion(menu, isShow) {
    // 修改展开状态
    menu.isExpanded = isShow;

    // 修改子节点的现实状态
    var list = menu.childList;
    if (list == null) {
        return;
    }
    for (var i = 0; i < list.length; i++) {
        var childMenu = list[i];
        childMenu.isShow = isShow;
        childMenu.isExpanded = isShow;

        toggleChildMenuListRecursion(childMenu, isShow);
    }
}