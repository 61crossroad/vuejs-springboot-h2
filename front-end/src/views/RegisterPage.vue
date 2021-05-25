<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <Logo/>
        <form @submit.prevent="submitForm">
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" id="username" v-model="form.username">
          </div>
          <div class="form-group">
            <label for="emailAddress">Email address</label>
            <input type="email" class="form-control" id="emailAddress" v-model="form.emailAddress">
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password">
          </div>
          <button type="submit" class="btn btn-primary btn-block">Create account</button>
        </form>
      </div>
    </div>
    <PageFooter/>
  </div>
</template>

<script>
import registrationService from '@/services/registration'
import { required, email, minLength, maxLength, alphaNum } from 'vuelidate/lib/validators'
import Logo from '@/components/Logo.vue'
import PageFooter from '@/components/PageFooter.vue'

export default {
  name: 'RegisterPage',
  data: function () {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: ''
      },
      errorMessage: ''
    }
  },
  components: {
    Logo,
    PageFooter
  },
  validations: {
    form: {
      username: {
        required,
        minLength: minLength(2),
        maxLength: maxLength(50),
        alphaNum
      },
      emailAddress: {
        required,
        email,
        maxLength: maxLength(100)
      },
      password: {
        required,
        minLength: minLength(6),
        maxLength: maxLength(30)
      }
    }
  },
  methods: {
    submitForm () {
      this.$v.$touch()
      if (this.$v.$invalid) {
        return
      }
      registrationService.register(this.form)
        .then(() => {
          this.$router.push({ name: 'LoginPage' })
        })
        .catch((error) => {
          this.errorMessage = 'Failed to register user. Reason: ' +
            (error.message ? error.message : 'Unknown') + '.'
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.container { max-width: 900px; }

.register-form {
  margin-top: 50px; max-width: 320px;

  .form-group {
    margin: 10px 0px;

    label {
      margin-bottom: 5px;
      font-weight: bold;
      color: #555;
    }
  }
  button {
      width: 100%;
      margin: 5px 0px;
  }
}
</style>
