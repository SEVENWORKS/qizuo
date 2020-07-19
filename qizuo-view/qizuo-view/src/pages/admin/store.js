import Vue from 'vue'
import Vuex from 'vuex'
import commonStore from '@/commonStore/index'
Vue.use(Vuex)

let module={
    state: {
    },
    mutations: {
    },
    actions: {
    }
}

export default new Vuex.Store({
    modules:Object.assign(commonStore,module),
    getters:{}
})
