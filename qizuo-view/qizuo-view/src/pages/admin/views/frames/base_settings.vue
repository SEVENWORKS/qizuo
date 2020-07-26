<template>
  <div>
    <a id="settings" href="#changeSkin" data-toggle="modal">
      <i class="fa fa-gear"></i>
      Change Skin
    </a>

    <div
      class="modal fade"
      id="changeSkin"
      tabindex="-1"
      role="dialog"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-hidden="true"
            >
              &times;
            </button>
            <h4 class="modal-title">Change Template Skin</h4>
          </div>
          <div class="modal-body">
            <div class="row template-skins">
              <a
                data-skin="skin-blur-violate"
                class="col-sm-2 col-xs-4"
                href=""
              >
                <img src="/static/img/frame/body/skin-violate.jpg" alt="" />
              </a>
              <a data-skin="skin-blur-lights" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-lights.jpg" alt="" />
              </a>
              <a data-skin="skin-blur-city" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-city.jpg" alt="" />
              </a>
              <a
                data-skin="skin-blur-greenish"
                class="col-sm-2 col-xs-4"
                href=""
              >
                <img src="/static/img/frame/body/skin-greenish.jpg" alt="" />
              </a>
              <a data-skin="skin-blur-night" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-night.jpg" alt="" />
              </a>
              <a data-skin="skin-blur-blue" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-blue.jpg" alt="" />
              </a>
              <a data-skin="skin-blur-sunny" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-sunny.jpg" alt="" />
              </a>
              <a data-skin="skin-cloth" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-cloth.jpg" alt="" />
              </a>
              <a data-skin="skin-tectile" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-tectile.jpg" alt="" />
              </a>
              <a data-skin="skin-blur-chrome" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-chrome.jpg" alt="" />
              </a>
              <a data-skin="skin-blur-ocean" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-ocean.jpg" alt="" />
              </a>
              <a data-skin="skin-blur-sunset" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-sunset.jpg" alt="" />
              </a>
              <a data-skin="skin-blur-yellow" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-yellow.jpg" alt="" />
              </a>
              <a data-skin="skin-blur-kiwi" class="col-sm-2 col-xs-4" href="">
                <img src="/static/img/frame/body/skin-kiwi.jpg" alt="" />
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  methods: {
    init() {
      $("#main").prepend(settings);
      $("body").on("click", ".template-skins > a", function (e) {
        e.preventDefault();
        var skin = $(this).data("skin");
        $("body").attr("id", skin);
        $("#changeSkin").modal("hide");
      });
    },
  },
};
</script>
<style lang="scss" scoped></style>
