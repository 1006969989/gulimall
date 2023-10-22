<template>
  <div>
    <el-tree :data="menus" :props="defaultProps"
             :expand-on-click-node="true"
             ref="treeMenus"
             @node-click="categoryClick"
             node-key="catId">
    </el-tree>
  </div>
</template>

<script>
export default {
  data () {
    return {
      menus: [],
      pId: [],
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
    categoryClick (data, node, component) {
      // console.log(data, node, component)
      this.$emit('category-node-click', data, node, component)
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
