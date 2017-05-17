<template>
  <div class="btn_add">+ 添加</div>
</template>

<script>
export default {
  name: 'FullButton',
  components: {
  },
  data: function () {
    return {
    }
  },
  methods: {
  }
}
</script>

<style>
.btn_add {
	width: 100%;
	height: 38px;
	text-align: center;
	font-size: 12px;
	color: #2D2D2D;
	line-height: 36px;
	box-sizing: border-box;
	background-color: #FFFFFF;
	border: 1px dashed #3c8dbc;
	transition: all .2s ease-in-out;
	-webkit-transition: all .2s ease-in-out;
	cursor: pointer;
	margin: 0;
}
.btn_add:hover {
	color: #467CD4;
	background-color: #f8f8f8;
	border-color: #357ca5;
}
</style>
