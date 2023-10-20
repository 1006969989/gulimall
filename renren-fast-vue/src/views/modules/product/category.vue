<template>
  <div>
    <el-switch
      v-model="draggable"
      active-text="开启拖拽"
      inactive-text="关闭拖拽">
    </el-switch>
    <el-button type="text"
               size="mini"
               v-if="draggable"
               @click="() => batchSave()">
      批量保存
    </el-button>
    <el-button type="danger"
               size="mini"
               @click="() => batchRemove()">
      批量删除
    </el-button>
    <el-tree :data="menus" :props="defaultProps"
             @node-click="handleNodeClick"
             :expand-on-click-node="false"
             :default-expanded-keys="openKey"
             show-checkbox
             :draggable="draggable"
             :allow-drop="allowDrop"
             @node-drop="handleDrop"
             ref="treeMenus"
             node-key="catId">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button v-if="node.level<=2 "
                     type="text"
                     size="mini"
                     @click="() => append(data)">
            Append
          </el-button>
          <el-button type="text"
                     size="mini"
                     @click="() => edit(data)">
            Edit
          </el-button>
          <el-button v-if="node.childNodes.length==0"
                     type="text"
                     size="mini"
                     @click="() => remove(node, data)">
            Delete
          </el-button>
        </span>
      </span>
    </el-tree>
    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
      width="30%">
      <el-form ref="form" :model="category" label-width="80px">
        <el-form-item label="分类名称">
          <el-input v-model="category.name"></el-input>
        </el-form-item>
        <el-form-item label="图标">
          <el-input v-model="category.icon"></el-input>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input v-model="category.productUnit"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="subCategory()">确 定</el-button>
  </span>
    </el-dialog>
  </div>

</template>

<script>
export default {
  data () {
    return {
      menus: [],
      openKey: [],
      dialogVisible: false,
      dialogType: '',
      title: '',
      maxLevel: 0,
      draggable: false,
      pId: [],
      updateNodes: [],
      category: {name: '', catLevel: 0, sort: 0, parentCid: 0, showStatus: 1, catId: null, icon: '', productUnit: ''},
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },
  // import 引入的组件需要注入到对象中才能使用
  components: {},
  props: {},
  // 计算属性 类似于 data 概念
  computed: {},
  // 监控 data 中的数据变化
  watch: {},
  // 方法集合
  methods: {
    handleNodeClick (data) {
      console.log(data)
    },
    batchRemove () {
      let cherkedNodes = this.$refs.treeMenus.getCheckedNodes()
      console.log(cherkedNodes)
      let ids = []
      let names = []
      for (let i = 0; i < cherkedNodes.length; i++) {
        console.log(cherkedNodes[i].catId)
        ids.push(cherkedNodes[i].catId)
        names.push(cherkedNodes[i].name)
      }
      this.$confirm(`是否批量删除分类: ${names}, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          this.$message({
            type: 'success',
            message: '批量删除成功!'
          })
          // 刷新页面
          this.getMeuns()
        })
      }).catch(() => {
      })
    },
    batchSave () {
      this.$http({
        url: this.$http.adornUrl('/product/category/update/sort'),
        method: 'post',
        data: this.$http.adornData(this.updateNodes, false)
      }).then(({data}) => {
        this.$message({
          type: 'success',
          message: '拖拽修改分类成功!'
        })
        // 刷新页面
        this.getMeuns()
        // 默认打开删除节点父节点
        this.openKey = this.pId
        this.updateNodes = []
        this.maxLevel = 0
      })
    },
    handleDrop (draggingNode, dropNode, dropType, ev) {
      // console.log('tree drop: ', dropNode.label, dropType)
      // 获取拖拽后当前节点的父节点ID
      // eslint-disable-next-line no-unused-vars
      let pId = 0
      // eslint-disable-next-line no-unused-vars
      let sibling = null
      if (dropType === 'inner') {
        pId = dropNode.data.catId
        sibling = dropNode.childNodes
      } else {
        pId = dropNode.parent.data.catId === undefined ? 0 : dropNode.parent.data.catId
        sibling = dropNode.parent.childNodes
      }
      // 修改拖拽后所有变化集合的顺序
      for (let i = 0; i < sibling.length; i++) {
        // 遍历到当前拖拽元素
        if (sibling[i].data.catId === draggingNode.data.catId) {
          // 层级发生变化
          let updateLevel = draggingNode.level
          if (sibling[i].level !== updateLevel) {
            // 同步当前层级
            updateLevel = sibling[i].level
            // 同步所有子节点层级
            this.updateChildLevel(sibling[i])
          }
          this.updateNodes.push({catId: sibling[i].data.catId, sort: i, parentCid: pId, catLevel: updateLevel})
        } else {
          this.updateNodes.push({catId: sibling[i].data.catId, sort: i})
        }
      }
      this.pId.push(pId)
      // 修改拖拽后集合层级
      console.log(this.updateNodes)
    },
    updateChildLevel (node) {
      if (node.childNodes.length > 0) {
        for (let i = 0; i < node.childNodes.length; i++) {
          let uNode = node.childNodes[i].data
          this.updateNodes.push({catId: uNode.catId, catLevel: node.childNodes[i].level})
          this.updateChildLevel(node.childNodes[i])
        }
      }
    },
    allowDrop (draggingNode, dropNode, type) {
      console.log(draggingNode, dropNode, type)

      // 计算当前节点最深层级
      this.countMaxLevel(draggingNode.data)
      // 计算当前拖拽的层级
      console.log((this.maxLevel - draggingNode.data.catLevel) + 1)
      let count = (this.maxLevel - draggingNode.data.catLevel) + 1
      return !(type === 'inner' && (count + dropNode.data.catLevel) > 3)
    },
    countMaxLevel (node) {
      if (node.children != null && node.children.length > 0) {
        for (let i = 0; i < node.children.length; i++) {
          if (node.children[i].catLevel > this.maxLevel) {
            this.maxLevel = node.children[i].catLevel
          }
          this.countMaxLevel(node.children[i])
        }
      }
    },
    getMeuns () {
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get'
      }).then(({data}) => {
        console.log('成功获取data数据')
        console.log(data.data)
        this.menus = data.data
      })
    },
    edit (data) {
      this.dialogType = 'edit'
      this.title = '编辑商品分类'
      this.dialogVisible = true
      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: 'get',
        data: this.$http.adornData('', false)
      }).then(({data}) => {
        console.log(data)
        this.category.name = data.data.name
        this.category.icon = data.data.icon
        this.category.catId = data.data.catId
        this.category.productUnit = data.data.productUnit
        this.category.parentCid = data.data.parentCid
      })
    },
    append (data) {
      console.log(data)
      this.dialogType = 'add'
      this.title = '添加商品分类'
      this.category.catLevel = data.catLevel * 1 + 1
      this.category.parentCid = data.catId
      this.dialogVisible = true
      this.category.icon = ''
      this.category.productUnit = ''
      this.category.name = ''
      this.category.catId = null
    },
    subCategory () {
      if (this.dialogType === 'add') {
        this.addCategory()
      }
      if (this.dialogType === 'edit') {
        this.editCategory()
      }
    },
    addCategory () {
      console.log(this.category)
      this.$http({
        url: this.$http.adornUrl('/product/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({data}) => {
        this.$message({
          type: 'success',
          message: '添加分类成功!'
        })
        // 刷新页面
        this.getMeuns()
        this.dialogVisible = false
        // 默认打开删除节点父节点
        this.openKey = [this.category.parentCid]
      })
    },
    editCategory () {
      var { catId, name, icon, productUnit } = this.category
      console.log('edit===')
      this.$http({
        url: this.$http.adornUrl('/product/category/update'),
        method: 'post',
        data: this.$http.adornData({ catId, name, icon, productUnit }, false)
      }).then(({data}) => {
        this.$message({
          type: 'success',
          message: '编辑分类成功!'
        })
        // 刷新页面
        this.getMeuns()
        this.dialogVisible = false
        // 默认打开删除节点父节点
        this.openKey = [this.category.parentCid]
      })
    },
    remove (node, data) {
      var ids = [data.catId]
      console.log(ids)
      this.$confirm(`是否删除分类${data.name}, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'post',
          data: this.$http.adornData(ids, false)
        }).then(({data}) => {
          console.log('remove', node, data)
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          // 刷新页面
          this.getMeuns()
          console.log(node.parent.data.catId)
          // 默认打开删除节点父节点
          this.openKey = [node.parent.data.catId]
        })
      }).catch(() => {
      })
    }
  },
  // 生命周期 - 创建完成（可以访问当前 this 实例）
  created () {
    this.getMeuns()
  },
  // 生命周期 - 挂载完成（可以访问 DOM 元素）
  mounted () {

  },
  beforeCreate () {}, // 生命周期 - 创建之前",
  beforeMount () {}, // 生命周期 - 挂载之前",
  beforeUpdate () {}, // 生命周期 - 更新之前",
  updated () {}, // 生命周期 - 更新之后",
  beforeDestroy () {}, // 生命周期 - 销毁之前",
  destroyed () {}, // 生命周期 - 销毁完成",
  activated () {} // 如果页面有 keep-alive 缓存功能，这个函数会触发
}
</script>
<style>

</style>
