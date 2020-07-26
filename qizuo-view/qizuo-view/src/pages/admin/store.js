import Vue from 'vue'
import Vuex from 'vuex'
import commonStore from '@/commonStore/index'
import commonGetters from '@/commonStore/getters'
Vue.use(Vuex)

let module={
    admin:{
        state: {
        },
        mutations: {
        },
        actions: {
        }
    }
}

export default new Vuex.Store({
    modules:Object.assign(commonStore,module),
    getters:commonGetters
})
