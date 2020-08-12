import store from '@store'

const commonModule={
    state: {
    },
    mutations: {
    },
    actions: {
    },
    namespaced: true,
}

store.registerModule('commonModule',commonModule)

export default store