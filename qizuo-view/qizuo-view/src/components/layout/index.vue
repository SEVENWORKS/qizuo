<template>
  <div class="layout_container">
    <!-- 菜单 -->
    <el-menu :default-active="defaultActive" class="layout_menu" :collapse="true">
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
  data(){
    return {
      defaultActive:'0-0'
    }
  },
  computed:{
    menu(){
        return window.$global.sysMenu;
    }
  },
  mounted(){
    //刷新菜单重新记录
    const indexPath=this.$route.path;
    for(let i=0;i<this.menu.length;i++){
      const item=this.menu[i];
      for(let j=0;j<item.child.length;j++){
        if(indexPath==item.child[j].url){
          this.defaultActive=i+'-'+j;
          break;
        }
      }

      if(this.defaultActive!='0-0'){
        break;
      }
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
