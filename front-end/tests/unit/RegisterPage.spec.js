import Vue from 'vue'
import VueRouter from 'vue-router'
import { mount, createLocalVue } from '@vue/test-utils'
import RegisterPage from '@/views/RegisterPage'
import Vuelidate from 'vuelidate'
import registrationService from '@/services/registration'

const localVue = createLocalVue()
localVue.use(VueRouter)
localVue.use(Vuelidate)
const router = new VueRouter()

jest.mock('@/services/registration')

describe('RegisterPage.vue', () => {
  let wrapper
  let fieldUsername
  let fieldEmailAddress
  let fieldPassword
  let buttonSubmit
  // let registerSpy

  beforeEach(() => {
    wrapper = mount(RegisterPage, {
      localVue,
      router
    })
    fieldUsername = wrapper.find('#username')
    fieldEmailAddress = wrapper.find('#emailAddress')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')

    // registerSpy = jest.spyOn(registrationService, 'register')
  })

  afterAll(() => {
    jest.restoreAllMocks()
    // registerSpy.mockReset()
    // registerSpy.mockRestore()
  })

  it('should render registration form', () => {
    expect(wrapper.find('.logo').attributes().src)
      .toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text())
      .toEqual('Open source task management tool')
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldEmailAddress.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(buttonSubmit.text()).toEqual('Create account')
  })

  it('should contain data model with initial values', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  it('should have form inputs bound with data model', () => {
    const username = 'sunny'
    const emailAddress = 'sunny@local'
    const password = 'VueJsRocks!'

    wrapper.vm.form.username = username
    wrapper.vm.form.emailAddress = emailAddress
    wrapper.vm.form.password = password

    // Vue updates DOM async, use callback of Vue.nextTick
    Vue.nextTick(() => {
      expect(fieldUsername.element.value).toEqual(username)
      expect(fieldEmailAddress.element.value).toEqual(emailAddress)
      expect(fieldPassword.element.value).toEqual(password)
    })
  })

  /* setMethods is deprecated
  it('should have form submit event handler `submitForm`', () => {
    const stub = jest.fn()
    wrapper.setMethods({submitForm: stub})
    buttonSubmit.trigger('submit')
    expect(stub).toBeCalled()
  })
  */

  // async, await
  it('should register when it is a new user', async () => {
    expect.assertions(2)
    const spy = jest.spyOn(registrationService, 'register')
    const stub = jest.fn()
    wrapper.vm.$router.push = stub
    wrapper.vm.form.username = 'sunny'
    wrapper.vm.form.emailAddress = "sunny@local.com"
    wrapper.vm.form.password = 'Jest!Jest!'
    await wrapper.vm.$nextTick()
    wrapper.vm.submitForm()
    expect(spy).toBeCalled()
    await wrapper.vm.$nextTick(() => {
      expect(stub).toHaveBeenCalledWith({name: 'LoginPage'})
    })
    spy.mockReset()
    spy.mockRestore()
  })

  it('should fail it is not a new user', async () => {
    // expect.assertions(3)

    wrapper.vm.form.emailAddress = 'ted@local.com'
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    await wrapper.vm.$nextTick()
    wrapper.vm.submitForm()
    // expect(registerSpy).toBeCalled()
    await wrapper.vm.$nextTick(null, () => {
      expect(wrapper.find('.failed').isVisible()).toBe(true)
    })
  })

  it('should fail when the email address is invalid', () => {
    const spy = jest.spyOn(registrationService, 'register')
    wrapper.vm.form.emailAddress = 'bad-email-address'
    wrapper.vm.submitForm()
    expect(spy).not.toHaveBeenCalled()
    spy.mockReset()
    spy.mockRestore()
  })
})
