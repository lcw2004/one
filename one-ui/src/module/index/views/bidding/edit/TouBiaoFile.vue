<template>
<div>
  <ul class="pubaplb">
    <li class="pubaplb_each">
      <div class="pubaplb_over">
        <i class="pubaplb_pic"><img src="../../../../../assets/image/icon_pdf1.png"></i>
        <a class="pubaplb_word1">预览</a>
      </div>
      <a class="pubaplb_edit"><img src="../../../../../assets/image/icon_bianji1.png"></a>
      <p class="pubaplb_name">《完整投标文件》</p>
    </li>
    <li class="pubaplb_each">
      <div class="pubaplb_load">
        <i class="pubaplb_pic"><img src="../../../../../assets/image/icon_pdf0.png"></i>
        <a class="pubaplb_twig">
          <p style="width:30%;"></p>
        </a>
      </div>
      <p class="pubaplb_name">《技术文件（不含报价）》</p>
    </li>
    <li class="pubaplb_each">
      <div class="pubaplb_add">
        <i class="pubaplb_tadd"></i>
        <a class="pubaplb_word2">上传文件</a>
      </div>
      <p class="pubaplb_name">《商务文件（含报价）》</p>
    </li>
  </ul>
</div>
</template>

<script>
export default {
}
</script>

<style>
.pubaplb_each {
  display: inline-block;
  width: 145px;
  margin: 0 5px;
  vertical-align: top;
  position: relative;
}

.pubaplb_over {
  width: 93px;
  height: 102px;
  background-color: #ffffff;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  margin: 0 auto;
  cursor: pointer;
}

.pubaplb_each:hover .pubaplb_edit {
  display: block;
}

.pubaplb_load {
  width: 93px;
  height: 102px;
  background-color: #ffffff;
  border: 1px solid #e5e5e5;
  border-radius: 4px;
  margin: 0 auto;
  cursor: default;
}

.pubaplb_add {
  width: 91px;
  height: 100px;
  background-color: #ffffff;
  border: 2px dashed #e5e5e5;
  border-radius: 4px;
  margin: 0 auto;
  cursor: pointer;
}

.pubaplb_pic {
  display: block;
  padding: 25px 0 0 0;
  text-align: center;
}

.pubaplb_word1 {
  display: block;
  font-size: 12px;
  color: #467cd4;
  margin: 10px 0 0 0;
  text-align: center;
}

.pubaplb_word2 {
  display: block;
  font-size: 12px;
  color: #9c9c9c;
  margin: 12px 0 0 0;
  text-align: center;
  transition: all .2s ease-in-out;
  -webkit-transition: all .2s ease-in-out;
}

.pubaplb_edit {
  width: 30px;
  height: 30px;
  background-color: rgba(70, 124, 212, 0.9);
  border-radius: 50%;
  text-align: center;
  cursor: pointer;
  position: absolute;
  top: -15px;
  right: 10px;
  display: none;
  transition: all .2s ease-in-out;
  -webkit-transition: all .2s ease-in-out;
}

.pubaplb_edit img {
  display: inline-block;
  margin: 8px -2px 0 0;
}

.pubaplb_edit:hover {
  background-color: rgba(70, 124, 212, 1);
}

.pubaplb_twig {
  display: block;
  width: 68px;
  height: 2px;
  background-color: #cfcfcf;
  margin: 15px auto 0 auto;
}

.pubaplb_twig p {
  height: 2px;
  background-color: #7190bc;
}

.pubaplb_tadd {
  display: block;
  width: 30px;
  height: 30px;
  margin: 25px auto 0 auto;
  position: relative;
}

.pubaplb_tadd:before {
  content: "";
  display: block;
  width: 8px;
  height: 30px;
  background-color: #dbdbdb;
  border-radius: 2px;
  margin: 0 auto;
  transition: all .2s ease-in-out;
  -webkit-transition: all .2s ease-in-out;
}

.pubaplb_tadd:after {
  content: "";
  display: block;
  width: 30px;
  height: 8px;
  background-color: #dbdbdb;
  border-radius: 2px;
  position: absolute;
  top: 12px;
  left: 0;
  transition: all .2s ease-in-out;
  -webkit-transition: all .2s ease-in-out;
}

.pubaplb_add:hover .pubaplb_tadd:before,
.pubaplb_add:hover .pubaplb_tadd:after {
  background-color: #467cd4;
}

.pubaplb_add:hover .pubaplb_word2 {
  color: #467cd4;
}

.pubaplb_name {
  height: 35px;
  font-size: 12px;
  color: #2d2d2d;
  text-align: center;
  margin: 10px 0 0 0;
  line-height: 18px;
}
</style>
