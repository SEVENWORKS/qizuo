<template>
  <div class="block-area">
    <!-- 弹框 -->
    <qz-block-title>弹框</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-dialog-index')">基本弹框</qz-button>
      <qz-button @click.native="open('qz-alert')">alert弹框</qz-button>
    </div>
    <!-- 布局 -->
    <qz-block-title>布局</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-animate')">动画</qz-button>
      <qz-button @click.native="open('qz-hr')">横线</qz-button>
      <qz-button @click.native="open('qz-panel')">普通panel</qz-button>
      <qz-button @click.native="open('qz-well')">基本panel2</qz-button>
    </div>
    <!-- 媒体 -->
    <qz-block-title>媒体</qz-block-title>
    <div></div>
    <!-- 分页 -->
    <qz-block-title>分页</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-page-index')">分页</qz-button>
    </div>
    <!-- 表格 -->
    <qz-block-title>表格</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-table-index')">基本表格</qz-button>
      <qz-button @click.native="open('qz-table-base')">基本使用表格</qz-button>
    </div>
    <!-- 树 -->
    <qz-block-title>树</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-tree-index')">树</qz-button>
    </div>

    <!-- 展示容器 -->
    <qz-dialog-index :show.sync="isShow" height="60%" width="60%">
      <template #header>
        <!-- 弹框 -->
        <qz-dialog-index
          v-if="dialogContent == 'qz-dialog-index'"
          :show.sync="isShow"
          height="80%"
          width="80%"
          :zIndex="99999"
        ></qz-dialog-index>
        <qz-alert v-if="dialogContent == 'qz-alert'"></qz-alert>
        <!-- 媒体 -->
        <!-- 布局 -->
        <qz-animate v-if="dialogContent == 'qz-animate'"></qz-animate>
        <qz-hr v-if="dialogContent == 'qz-hr'"></qz-hr>
        <qz-panel v-if="dialogContent == 'qz-panel'">
          <template #header>
            <h3 class="panel-title">Panel title</h3>
          </template>
          <template #body>
            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
            scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum
            in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac
            nisi vulputate fringilla. Donec lacinia congue felis in faucibus.
          </template>
          <template #footer>
            Panel footer
          </template>
        </qz-panel>
        <qz-well v-if="dialogContent == 'qz-well'">
          Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
          scelerisque ante sollicitudin commodo. Cras purus odio, vestibulum in
          vulputate at, tempus viverra turpis. Fusce condimentum nunc ac nisi
          vulputate fringilla. Donec lacinia congue felis in faucibus.
        </qz-well>
        <!-- 分页 -->
        <qz-page-index v-if="dialogContent == 'qz-page-index'"></qz-page-index>
        <!-- 表格 -->
        <qz-table-index
          v-if="dialogContent == 'qz-table-index'"
        ></qz-table-index>
        <qz-table-base v-if="dialogContent == 'qz-table-base'"></qz-table-base>
        <!-- 树 -->
        <qz-tree-index v-if="dialogContent == 'qz-tree-index'"></qz-tree-index>
      </template>
    </qz-dialog-index>
  </div>
</template>
<script>
export default {
  data() {
    return {
      isShow: false, //是否显示
      dialogContent: "", //dialog内容
    };
  },
  methods: {
    open(val) {
      //打开弹框
      this.isShow = true;
      //限制显示
      this.dialogContent = val;
    },
  },
  watch: {
    isShow(newVal) {
      if (!newVal) {
        this.dialogContent = "";
      }
    },
  },
};
</script>
<style lang="scss" scoped></style>
