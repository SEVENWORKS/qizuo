<template>
  <index :show="isShow">
    <template #header>
      {{ alertMsg }}
    </template>
  </index>
</template>

<script>
import index from "./index";
import { mapState, mapMutations } from "../../../../../node_modules/vuex";
export default {
  components: {
    index,
  },
  data() {
    return {
      isShow: false,
    };
  },
  computed: {
    ...mapState(["alertMsg"]),
  },
  methods: {
    ...mapMutations(["updateAlertMsg"]),
  },
  watch: {
    //监听弹框消息
    alertMsg(newVal, oldVal) {
      if (newVal) {
        this.isShow = true;
        setTimeout(() => {
          this.isShow = false;
          this.updateAlertMsg("");
        }, 1500);
      }
    },
  },
};
</script>

<style scoped></style>
