<template>
  <div class="block-area">
    <!-- 日历 -->
    <qz-block-title>日历</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-calendar')">日历</qz-button>
    </div>
    <!-- 弹框 -->
    <qz-block-title>弹框</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-dialog-index')">基本弹框</qz-button>
      <qz-button @click.native="open('qz-alert')">alert弹框</qz-button>
      <qz-button @click.native="open('qz-alert-theme')"
        >alert主题弹框</qz-button
      >
      <qz-button @click.native="open('qz-dropdown')">表单下拉弹框</qz-button>
      <qz-button @click.native="open('qz-modal')">模态弹框</qz-button>
      <qz-button @click.native="open('qz-popover')">悬浮弹框</qz-button>
      <qz-button @click.native="open('qz-tooltips')">tips弹框</qz-button>
    </div>
    <!-- 表单 -->
    <qz-block-title>表单</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-form-index')">基本表单</qz-button>
      <qz-button @click.native="open('qz-checkbox')">checkbox</qz-button>
      <qz-button @click.native="open('qz-img')">img</qz-button>
      <qz-button @click.native="open('qz-input')">input</qz-button>
      <qz-button @click.native="open('qz-radio')">radio</qz-button>
      <qz-button @click.native="open('qz-select')">select</qz-button>
      <qz-button @click.native="open('qz-textarea')">textarea</qz-button>
      <qz-button @click.native="open('qz-textarea-editor')">富文本</qz-button>
      <qz-button @click.native="open('qz-upload')">上传</qz-button>
      <qz-button @click.native="open('qz-button')">button</qz-button>
      <qz-button @click.native="open('qz-button-group')">button组</qz-button>
      <qz-button @click.native="open('qz-button-switch')">button开关</qz-button>
      <qz-button @click.native="open('qz-form-dropdown')">下拉块</qz-button>
      <qz-button @click.native="open('qz-slider')">拉条</qz-button>
      <qz-button @click.native="open('qz-slider-bar')">进度条</qz-button>
      <qz-button @click.native="open('qz-addresses')">地址段落</qz-button>
      <qz-button @click.native="open('qz-blockquote')">块段落</qz-button>
      <qz-button @click.native="open('qz-paragraph')">基本段落</qz-button>
      <qz-button @click.native="open('qz-paragraph-list')">段落列表</qz-button>
      <qz-button @click.native="open('qz-paragraph-list-table')"
        >段落表格列表</qz-button
      >
      <qz-button @click.native="open('qz-paragraph-media')">媒体段落</qz-button>
    </div>
    <!-- 布局 -->
    <qz-block-title>布局</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-layout-index')">基本布局</qz-button>
      <qz-button @click.native="open('qz-animate')">动画</qz-button>
      <qz-button @click.native="open('qz-block')">block块</qz-button>
      <qz-button @click.native="open('qz-hr')">横线</qz-button>
      <qz-button @click.native="open('qz-icon')">icon</qz-button>
      <qz-button @click.native="open('qz-label')">label</qz-button>
      <qz-button @click.native="open('qz-block-title')">块状title</qz-button>
      <qz-button @click.native="open('qz-title')">普通title</qz-button>
      <qz-button @click.native="open('qz-title-head')">头title</qz-button>
      <qz-button @click.native="open('qz-title-page')">page头title</qz-button>
      <qz-button @click.native="open('qz-chat')">聊天框</qz-button>
      <qz-button @click.native="open('qz-collapse')">抽屉</qz-button>
      <qz-button @click.native="open('qz-content-box')">块状box</qz-button>
      <qz-button @click.native="open('qz-jumbotron')">超大背景块</qz-button>
      <qz-button @click.native="open('qz-panel')">普通panel</qz-button>
      <qz-button @click.native="open('qz-panel-shortcut')">短panel</qz-button>
      <qz-button @click.native="open('qz-panel-title')"
        >带title的panel</qz-button
      >
      <qz-button @click.native="open('qz-tabs')">tabs</qz-button>
      <qz-button @click.native="open('qz-well')">布局</qz-button>
    </div>
    <!-- 媒体 -->
    <qz-block-title>媒体</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-audio')">声音</qz-button>
      <qz-button @click.native="open('qz-carousel')">轮播</qz-button>
      <qz-button @click.native="open('qz-lightbox')">图片lightbox</qz-button>
      <qz-button @click.native="open('qz-video')">视频</qz-button>
    </div>
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
      <qz-button @click.native="open('qz-list')">列表</qz-button>
    </div>
    <!-- 树 -->
    <qz-block-title>树</qz-block-title>
    <div>
      <qz-button @click.native="open('qz-tree-index')">树</qz-button>
    </div>

    <!-- 展示容器 -->
    <qz-dialog-index :show.sync="isShow" height="80%" width="80%">
      <template #header>
        <!-- 日历 -->
        <qz-calendar v-if="dialogContent == 'qz-calendar'"></qz-calendar>
        <!-- 弹框 -->
        <qz-dialog-index
          v-if="dialogContent == 'qz-dialog-index'"
          :show.sync="isShow"
          height="80%"
          width="80%"
          :zIndex="99999"
        ></qz-dialog-index>
        <qz-alert v-if="dialogContent == 'qz-alert'"></qz-alert>
        <qz-alert-theme
          v-if="dialogContent == 'qz-alert-theme'"
          :icon="true"
          :type="1"
          :close="true"
        >
          Heads up! This alert needs your attention, but it's not super
          important.
          <i class="icon">&#61770;</i>
        </qz-alert-theme>
        <qz-dropdown v-if="dialogContent == 'qz-dropdown'"></qz-dropdown>
        <qz-modal v-if="dialogContent == 'qz-modal'"></qz-modal>
        <qz-popover v-if="dialogContent == 'qz-popover'"></qz-popover>
        <qz-tooltips v-if="dialogContent == 'qz-tooltips'"></qz-tooltips>
        <!-- 表单 -->
        <qz-form-index v-if="dialogContent == 'qz-form-index'"></qz-form-index>
        <qz-checkbox v-if="dialogContent == 'qz-checkbox'"></qz-checkbox>
        <qz-img v-if="dialogContent == 'qz-img'"></qz-img>
        <qz-input v-if="dialogContent == 'qz-input'"></qz-input>
        <qz-radio v-if="dialogContent == 'qz-radio'"></qz-radio>
        <qz-select v-if="dialogContent == 'qz-select'"></qz-select>
        <qz-textarea v-if="dialogContent == 'qz-textarea'"></qz-textarea>
        <qz-textarea-editor
          v-if="dialogContent == 'qz-textarea-editor'"
        ></qz-textarea-editor>
        <qz-upload v-if="dialogContent == 'qz-upload'"></qz-upload>
        <qz-button v-if="dialogContent == 'qz-button'"></qz-button>
        <qz-button-group
          v-if="dialogContent == 'qz-button-group'"
        ></qz-button-group>
        <qz-button-switch
          v-if="dialogContent == 'qz-button-switch'"
        ></qz-button-switch>
        <qz-form-dropdown
          v-if="dialogContent == 'qz-form-dropdown'"
        ></qz-form-dropdown>
        <qz-slider v-if="dialogContent == 'qz-slider'"></qz-slider>
        <qz-slider-bar v-if="dialogContent == 'qz-slider-bar'"></qz-slider-bar>
        <qz-addresses v-if="dialogContent == 'qz-addresses'"></qz-addresses>
        <qz-blockquote v-if="dialogContent == 'qz-blockquote'"></qz-blockquote>
        <qz-paragraph v-if="dialogContent == 'qz-paragraph'"></qz-paragraph>
        <qz-paragraph-list
          v-if="dialogContent == 'qz-paragraph-list'"
        ></qz-paragraph-list>
        <qz-paragraph-list-table
          v-if="dialogContent == 'qz-paragraph-list-table'"
        ></qz-paragraph-list-table>
        <qz-paragraph-media
          v-if="dialogContent == 'qz-paragraph-media'"
        ></qz-paragraph-media>
        <!-- 媒体 -->
        <qz-audio v-if="dialogContent == 'qz-audio'"></qz-audio>
        <qz-carousel v-if="dialogContent == 'qz-carousel'"></qz-carousel>
        <qz-lightbox v-if="dialogContent == 'qz-lightbox'"></qz-lightbox>
        <qz-video v-if="dialogContent == 'qz-video'"></qz-video>
        <!-- 布局 -->
        <qz-layout-index
          v-if="dialogContent == 'qz-layout-index'"
        ></qz-layout-index>
        <qz-animate v-if="dialogContent == 'qz-animate'"></qz-animate>
        <qz-block v-if="dialogContent == 'qz-block'"></qz-block>
        <qz-hr v-if="dialogContent == 'qz-hr'"></qz-hr>
        <qz-icon v-if="dialogContent == 'qz-icon'"></qz-icon>
        <qz-label v-if="dialogContent == 'qz-label'"></qz-label>
        <qz-block-title
          v-if="dialogContent == 'qz-block-title'"
        ></qz-block-title>
        <qz-title v-if="dialogContent == 'qz-title'"></qz-title>
        <qz-title-head v-if="dialogContent == 'qz-title-head'"></qz-title-head>
        <qz-title-page v-if="dialogContent == 'qz-title-page'"></qz-title-page>
        <qz-chat v-if="dialogContent == 'qz-chat'"></qz-chat>
        <qz-collapse v-if="dialogContent == 'qz-collapse'"></qz-collapse>
        <qz-content-box
          v-if="dialogContent == 'qz-content-box'"
        ></qz-content-box>
        <qz-jumbotron v-if="dialogContent == 'qz-jumbotron'"></qz-jumbotron>
        <qz-panel v-if="dialogContent == 'qz-panel'"></qz-panel>
        <qz-panel-shortcut
          v-if="dialogContent == 'qz-panel-shortcut'"
        ></qz-panel-shortcut>
        <qz-panel-title
          v-if="dialogContent == 'qz-panel-title'"
        ></qz-panel-title>
        <qz-tabs v-if="dialogContent == 'qz-tabs'"></qz-tabs>
        <qz-well v-if="dialogContent == 'qz-well'"></qz-well>
        <!-- 分页 -->
        <qz-page-index v-if="dialogContent == 'qz-page-index'"></qz-page-index>
        <!-- 表格 -->
        <qz-table-index
          v-if="dialogContent == 'qz-table-index'"
        ></qz-table-index>
        <qz-table-base v-if="dialogContent == 'qz-table-base'"></qz-table-base>
        <qz-list v-if="dialogContent == 'qz-list'"></qz-list>
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
