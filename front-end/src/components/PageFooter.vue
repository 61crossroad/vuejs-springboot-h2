<template>
  <footer class="footer">
    <span class="copyright">2018 TaskAgile.com + 61crossroad</span>
    <ul class="footer-links list-inline float-right">
      <li class="list-inline-item"><a href="#">About</a></li>
      <li class="list-inline-item"><a href="#">Terms of Service</a></li>
      <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
      <li class="list-inline-item"><a href="https://github.com/61crossroad/vuejs-springboot-h2" target="_blank">Github</a></li>
    </ul>
  </footer>
</template>

<script>
export default {
  name: 'PageFooter'
}
</script>

<style lang="scss" scoped>
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
