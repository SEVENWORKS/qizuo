import Vue from 'vue'
import Vuex from 'vuex'
import commonStore from '@/commonStore/index'
Vue.use(Vuex)

let store={
    state: {
    },
    mutations: {
    },
    actions: {
    },
    modules: {
    }
}
Object.assign(store,commonStore)

export default new Vuex.Store(store)
