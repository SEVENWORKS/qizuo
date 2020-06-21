<template>
  <div class="base_error" ref="base_error">
    <section id="error-page" class="tile">
      <h1 class="m-b-10" style="margin-top: 20%;">ERROR 404</h1>
      <p>Sorry, but the page you are looking for has not been found.</p>
      <a class="underline" href="#">天堂</a> or
      <a class="underline" href="#">地狱</a>
    </section>
  </div>
</template>
<script>
export default {
  mounted() {
    document.getElementsByTagName("body")[0].appendChild(this.$refs.base_error);
  },
};
</script>
<style scoped>
.base_error {
  z-index: 10000;
}
</style>
