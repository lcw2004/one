import Vue from 'vue'

/**
 * 递归初始化菜单列表，将menu下的子菜单添加tempList中
 * */
function initMenu (menu, level, tempList) {
  let childList = menu.childList
  if (!childList) {
    return
  }
  for (let i = 0; i < childList.length; i++) {
    let childMenu = childList[i]
    childMenu.level = level
    Vue.set(childMenu, 'isShowInTable', level <= 3) // 是否显示，1,2,3级菜单默认显示
    Vue.set(childMenu, 'isExpanded', level <= 2) // 是否展开菜单，1,2,3级菜单默认展开
    tempList.push(childMenu)

    initMenu(childMenu, level + 1, tempList)
  }
}

/**
 * 切换下一级菜单的状态，主要用于打开，打开的时候只显示下一级，且只修改本级的显示状态
 * @param menu
 * @param isShowInTable
 */
function toggleChildMenuList (menu, isShowInTable) {
  // 修改子节点的显示状态
  let list = menu.childList
  if (list == null) {
    return
  }
  for (let i = 0; i < list.length; i++) {
    let childMenu = list[i]
    childMenu.isShowInTable = isShowInTable
  }
}

/**
 * 递归切换所有菜单的状态，主要用于关闭，关闭的时候需要修改所有子节点的展开及显示状态
 * @param menu
 * @param isShowInTable
 */
function toggleChildMenuListRecursion (menu, isShowInTable) {
  // 修改子节点的现实状态
  let list = menu.childList
  if (list == null) {
    return
  }
  for (let i = 0; i < list.length; i++) {
    let childMenu = list[i]
    childMenu.isShowInTable = isShowInTable
    childMenu.isExpanded = isShowInTable

    toggleChildMenuListRecursion(childMenu, isShowInTable)
  }
}

/**
 * 收缩 / 展开 全部菜单
 * @param topMenu
 * @param isShowInTable
 */
function toggleAllMenu (topMenu, isShowInTable) {
  let list = topMenu.childList
  for (let i = 0; i < list.length; i++) {
    let childMenu = list[i]
    childMenu.isExpanded = isShowInTable
    toggleChildMenuListRecursion(childMenu, isShowInTable)
  }
}

function removeFromTopElement (topElement, element) {
  let list = topElement.childList
  if (list == null) {
    return
  }
  list.forEach((childMenu, i) => {
    if (childMenu.id === element.id) {
      childMenu.childList = null
      list.splice(i, 1)
    }
    removeFromTopElement(childMenu, element)
  })
}

const TreeTableMiXin = {
  data: function () {
    return {
      /**
       * 展开状态
       */
      toggleStatus: true
    }
  },
  computed: {
    dataList: function () {
      let tempList = []
      if (this.topElement) {
        initMenu(this.topElement, 1, tempList)
      }
      return tempList
    }
  },
  methods: {
    /**
     * 展开/收缩 菜单
     */
    toggle: function (menu) {
      let isExpanded = menu.isExpanded
      if (isExpanded) {
        toggleChildMenuListRecursion(menu, !isExpanded)
      } else {
        toggleChildMenuList(menu, !isExpanded)
      }
      menu.isExpanded = !isExpanded
    },

    /**
     * 全部展开/收缩 菜单
     */
    toggleAll: function () {
      toggleAllMenu(this.topElement, !this.toggleStatus)
      this.toggleStatus = !this.toggleStatus
    },

    /**
     * 移除元素以及该元素的子元素
     */
    removeElement (element) {
      removeFromTopElement(this.topElement, element)
    }
  }
}

export default TreeTableMiXin
