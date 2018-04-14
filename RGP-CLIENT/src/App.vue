<template>
	<div id="app">
		<transition name="fade" mode="out-in">
			<router-view></router-view>
		</transition>
	</div>
</template>

<script>
  export default {
    name: 'App'
  }
</script>

<style lang="scss">
  body {
    margin: 0px;
    padding: 0px;
    font-size: 24px;
    background: #1F2D3D;
    -webkit-font-smoothing: antialiased;
    font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
    /*设置背景图片 background: url(assets/bg1.jpg) center !important; background-size: cover;*/
  }

  #app {
    top: 0px;
    bottom: 0px;
    width: 100%;
    position: absolute;
  }
</style>
