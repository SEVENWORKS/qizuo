<template>
  <div>
    <index>
      <template #header
        ><tr>
          <th width="10%">序号</th>
          <th v-for="(item, index) of header" :key="index">{{ item }}</th>
        </tr></template
      >
      <template #body
        ><tr v-for="(item, index) of data" :key="index" @click="trClick(item)">
          <td>{{ index + 1 }}</td>
          <td v-for="(item2, index2) of item" :key="index2">
            {{ item2 }}
          </td>
        </tr>
        <tr v-if="data.length <= 0">
          <td :colspan="header.length + 1">
            暂无数据
          </td>
        </tr>
      </template>
    </index>
    <!-- 弹框 -->
    <base-dialog :show="isShow">
      <template #header>
        <div>
          <button
            class="btn btn-sm btn-alt m-r-5"
            type="button"
            v-if="noshowAdd != 1"
          >
            新增
          </button>
          <button
            class="btn btn-sm btn-alt m-r-5"
            type="button"
            v-if="noshowDelete != 1"
          >
            删除
          </button>
          <button
            class="btn btn-sm btn-alt m-r-5"
            type="button"
            v-if="noshowUpdate != 1"
          >
            修改
          </button>
        </div>
      </template>
    </base-dialog>
  </div>
</template>

<script>
import index from "./index";
export default {
  props: [
    "header",
    "data",
    "doClick",
    "noshowUpdate",
    "noshowDelete",
    "noshowAdd",
  ],
  components: {
    index,
  },
  data() {
    return {
      isShow: false,
      item: null,
    };
  },
  methods: {
    trClick(item) {
      this.item = item;
      this.isShow = true;
    },
    add() {
      this.$emit("add", this.item);
    },
    delete() {
      this.$emit("delete", this.item);
    },
    update() {
      this.$emit("update", this.item);
    },
  },
};
</script>

<style scoped>
td,
th {
  text-align: center;
}
</style>
