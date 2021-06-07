// For authoring Nightwatch tests, see
// https://nightwatchjs.org/guide

module.exports = {
  'login test': function (browser) {
    browser
    .url(process.env.VUE_DEV_SERVER_URL + 'login')
    .waitForElementVisible('#app', 5000)
    // refactored
    // .assert.containsText('h1', 'TaskAgile')
    .end()
  }
}
