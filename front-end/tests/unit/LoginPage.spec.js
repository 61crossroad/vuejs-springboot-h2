import Vue from 'vue'
import LoginPage from '@/views/LoginPage'

describe('LoginPage.vue', () => {
  it('should render correct contents', () => {
    const Constructor = Vue.extend(LoginPage)
    const vm = new Constructor().$mount()
    // refactored
    // expect(vm.$el.querySelector('h1').textContent)
    // .toEqual('TaskAgile')
  })
})
