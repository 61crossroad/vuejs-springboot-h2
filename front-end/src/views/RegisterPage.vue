<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img class="logo" src="/static/images/logo.png">
          <div class="tagline">Open source task management tool</div>
        </div>
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
          <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Create account</button>
          </div>
        </form>
      </div>
    </div>
    <footer class="footer">
      <span class="copyright">2018 TaskAgile.com + 61crossroad</span>
      <ul class="footer-links list-inline float-right">
        <li class="list-inline-item"><a href="#">About</a></li>
        <li class="list-inline-item"><a href="https://github.com/61crossroad/vuejs-springboot-h2" target="_blank">Github</a></li>
      </ul>
    </footer>
  </div>
</template>

<script>
import registrationService from '@/services/registration'

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
  methods: {
    submitForm () {
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
.register-form { margin-top: 50px; max-width: 320px; }
.logo-wrapper {
  text-align: center;
  margin-bottom: 40px;

  .tagline {
    line-height: 100%;
    color: #666;
  }

  .logo {
    max-width: 150px;
    margin: 0 auto;
  }
}

.register-form {
  .form-group {
    margin: 10px 0px;
    label {
      margin-bottom: 5px;
      font-weight: bold;
      color: #555;
    }
    button {
      width: 100%;
      margin: 5px 0px;
    }
  }
}

.footer {
  width: 100%;
  font-size: 13px;
  color: #666;
  line-height: 40px;
  border-top: 1px solid #ddd;
  margin-top: 50px;

  .footer-links {
    display: inline-flex;
    float: right;
  }

  .list-inline-item {
    margin-right: 10px;
  }

  a {
    color: #666;
  }
}
</style>
