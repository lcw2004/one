# 图片预览组件

## 预览列表

图片列表：

    list: [{
      src: 'https://farm4.staticflickr.com/3894/15008518202_c265dfa55f_h.jpg',
      w: 600,
      h: 400
    }, {
      src: 'https://farm6.staticflickr.com/5591/15008867125_b61960af01_h.jpg',
      w: 1200,
      h: 900
    }]

接口：

    $preview.openList(index, list)

示例：

    <img class="preview-img" v-for="(item, index) in list" :src="item.src" height="100" @click="$preview.openList(index, list)">

## 预览单个图片

图片：

    img: {
      src: 'https://farm4.staticflickr.com/3894/15008518202_c265dfa55f_h.jpg',
      w: 600,
      h: 400
    }

接口：

    $preview.open(index, img)

示例：

    <img class="preview-img" :src="img.src" height="100" @click="$preview.openImg(img)">
