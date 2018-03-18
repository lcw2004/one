<template>
  <div class="about-page">
    <div class="about-header">
      <div class="about-header-inner">
        <div class="text-left about-back" v-if="showBack">
          <a href="/login.html">
            <i class="fa fa-arrow-circle-o-left"></i>
            返回登录页面
          </a>
        </div>
        <div slot="header">
          <h1 class="profile-name">{{ title }}</h1>
        </div>
      </div>
    </div>
    <div class="about-content">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      require: true
    },
    showBack: {
      type: Boolean,
      default: true
    }
  },
  data: function () {
    return {
      isContainer: true
    }
  }
}
</script>

<style scoped lang="less">
.about-page {
  background: #f7f7f7;
  margin-bottom: 50px;
}

.about-header {
  color: #fff;
  background-color: #357ca5;
}

.about-header-inner {
  position: relative;
  z-index: 2;
  padding: 55px 20px 85px 20px;
  text-align: center;
}

.about-content {
  z-index: 4;
  background: #fff;
  max-width: 930px;
  margin: -50px auto 20px auto;
  @media (max-width: 660px) {
    margin-left: 15px;
    margin-right: 15px;
  }
  padding: 10px 20px 10px 20px;
  box-shadow: 0px 3px 10px rgba(0, 0, 0, .2);
  z-index: 1020;
  color: #555;

  h2 {
    font-weight: 300;
    font-size: 1.6em;
    position: relative;
    padding-bottom: 10px;
    color: #22273f;
    margin-bottom: 20px;
    &:before {
      position: absolute;
      top: 100%;
      left: 0;
      content: " ";
      height: 2px;
      width: 40px;
      background: #ff7777;
    }
  }
  h3 {
    font-size: 1.2em;
    text-transform: capitalize;
    font-weight: normal;
    margin-top: 0;
  }
}

.about-back {
  position: absolute;
  top: 10px;
  left: 25px;

  a {
    color: white;
    text-decoration: none;

    &:hover{
      color: darken(white, 10%);
    }
  }
}

.profile-name {
  font-size: 1.9em;
  margin-top: 10px;
  display: block;
  letter-spacing: 2px;
  text-transform: uppercase;
}
</style>
