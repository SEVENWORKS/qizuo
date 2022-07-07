<template>
  <div class="layout_container">
    <!-- 菜单 -->
    <el-menu default-active="0-0" class="layout_menu" :collapse="true">
        <el-submenu :index="index+''" v-for="(item,index) of menu" :key="index">
            <template slot="title">
                <i :class="item.icon"></i>
                <span slot="title">{{item.name}}</span>
            </template>
            <el-menu-item :index="index+'-'+keyIndex" v-for="(key,keyIndex) of item.child" :key="keyIndex" @click="jump(key)">{{key.name}}</el-menu-item>
        </el-submenu>
    </el-menu>
    <!-- 主view -->
    <div class="layout_view">
        <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name:'layout',
  computed:{
    menu(){
        return window.$global.sysMenu;
    }
  },
  methods:{
    //菜单跳转
    jump(item){
        this.$router.push(item.url)
    }
  }
};
</script>
