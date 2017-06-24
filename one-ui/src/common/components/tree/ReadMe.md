# TreeTable使用

稍微优点复杂，能优化一下吗？

## 1. 引入mixins

    import TreeTableMiXin from '../../../../../common/mixins/TreeTableMiXin'
    mixins: [TreeTableMiXin],

## 2. 定义树顶级节点

    data: () => {
      return {
        topElement: {}
      }
    }

## 3. 表格中根据状态显示/隐藏tr

    <tr v-for="obj of dataList" v-show="obj.isShowInTable" :key="obj.id">
    </tr>

## 4. 第一列引入TreeTableColPrefix

    <td>
      <TreeTableColPrefix :obj="obj" @toggle="toggle(obj)">{{ obj.name }}</TreeTableColPrefix>
    </td>

    TreeTableColPrefix的挂载当前元素的名称

## 5. 显示 全部展开/全部收缩 按钮

    <a class="btn btn-primary" @click="toggleAll()"><span v-if="toggleStatus">收缩</span><span v-else>展开</span>全部</a>


# Tree组件的使用

## props
- element: Tree顶级节点
- select-type: 选择框类型：radio - 单选，checkbox - 多选

## v-model

## 示例

    <Tree :element="topElement" v-model="value" select-type="radio"></Tree>

# TreeModal组件的使用

## 1. 引入mixins

    import SelectTreeModal from '../../../components/tree/SelectTreeModal'

## 2. 定义loadTree方法

定义好loadTree方法之后，在modal组件的mounted事件中会自动调用loadTree方法加载数据，并用Tree组件显示出来

    methods: {
      loadTree () {
        let actions = {
          getTree: {method: 'get', url: '/api/sys/area/tree'}
        }
        let resource = this.$resource(null, {}, actions)
        resource.getTree().then((response) => {
          this.topElement = response.body
        })
      }
    }

## 3. 使用v-model接收数据
