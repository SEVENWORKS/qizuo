<!-- 表格分页组件 -->
<template>
  <div>
    <nav class="boot-nav">
      <ul class="pagination boot-page">
        <li>
          <a href="#" aria-label="Previous" @click="onFirstClick()">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li>
          <a href="#" aria-label="Next" @click="onPrevClick()">
            <span aria-hidden="true">‹</span>
          </a>
        </li>
        <li
          v-for="(page, $index) in pages"
          :class="activeNum === $index ? 'active' : ''"
        >
          <a href="#" v-text="page" @click="onPageClick($index)"></a>
        </li>
        <li>
          <a href="#" aria-label="Next" @click="onNextClick()">
            <span aria-hidden="true">›</span>
          </a>
        </li>
        <li>
          <a href="#" aria-label="Next" @click="onLastClick()">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
  props: {
    // 页码
    pageCount: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      activeNum: 0,
      pages: [],
    };
  },
  methods: {
    // 上一页
    onPrevClick() {
      // 当前页是否为当前最小页码
      if (this.activeNum > 0) {
        this.activeNum = this.activeNum - 1;
      }
    },
    // 下一页
    onNextClick() {
      // 当前页是否为当前最大页码
      if (this.activeNum < this.pages.length - 1) {
        this.activeNum = this.activeNum + 1;
      }
    },
    // 第一页
    onFirstClick() {
      if (this.pages[0] === 1) {
        this.activeNum = 0;
      }
    },
    // 最后一页
    onLastClick() {
      if (this.activeNum < this.pages.length - 1) {
        this.activeNum = this.pages.length - 1;
      }
    },
    // 点击页码刷新数据
    onPageClick(index) {
      this.activeNum = index;
    },
  },
  watch: {
    // 监测当前页变化
    activeNum(newVal, oldVal) {
      this.$emit("pageChange", newVal);
    },
    pageCount(newVal, oldVal) {
      if (newVal) {
        const lenBe = countDivision(newVal, this.$global.pageSize, 2);
        const lenBe2 = countDivision(newVal, this.$global.pageSize, 1);
        const len = lenBe > 0 ? lenBe2 + 1 : lenBe2;
        let paging = [];
        for (let i = 1; i <= len; i++) {
          paging.push(i);
        }
        this.pages = paging;
      }
    },
  },
};
</script>

<style scoped>
.boot-select {
  float: right;
  width: 80px;
}

.boot-nav {
  float: right;
}

.boot-page {
  display: inline-block;
  margin: 2px 10px 0 20px;
  vertical-align: middle;
}

.page-total {
  display: inline-block;
  vertical-align: middle;
}
</style>
