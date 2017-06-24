<template>
  <div class="pswp" tabindex="-1" role="dialog" aria-hidden="true">

    <!-- Background of PhotoSwipe.
         It's a separate element as animating opacity is faster than rgba(). -->
    <div class="pswp__bg"></div>

    <!-- Slides wrapper with overflow:hidden. -->
    <div class="pswp__scroll-wrap">

      <!-- Container that holds slides.
          PhotoSwipe keeps only 3 of them in the DOM to save memory.
          Don't modify these 3 pswp__item elements, data is added later on. -->
      <div class="pswp__container">
        <div class="pswp__item"></div>
        <div class="pswp__item"></div>
        <div class="pswp__item"></div>
      </div>

      <!-- Default (PhotoSwipeUI_Default) interface on top of sliding area. Can be changed. -->
      <div class="pswp__ui pswp__ui--hidden">

        <div class="pswp__top-bar">

          <!--  Controls are self-explanatory. Order can be changed. -->

          <div class="pswp__counter"></div>

          <button class="pswp__button pswp__button--close" title="Close (Esc)"></button>

          <button class="pswp__button pswp__button--share" title="Share"></button>

          <button class="pswp__button pswp__button--fs" title="Toggle fullscreen"></button>

          <button class="pswp__button pswp__button--zoom" title="Zoom in/out"></button>

          <!-- Preloader demo http://codepen.io/dimsemenov/pen/yyBWoR -->
          <!-- element will get class pswp__preloader--active when preloader is running -->
          <div class="pswp__preloader">
            <div class="pswp__preloader__icn">
              <div class="pswp__preloader__cut">
                <div class="pswp__preloader__donut"></div>
              </div>
            </div>
          </div>
        </div>

        <div class="pswp__share-modal pswp__share-modal--hidden pswp__single-tap">
          <div class="pswp__share-tooltip"></div>
        </div>

        <button class="pswp__button pswp__button--arrow--left" title="Previous (arrow left)">
        </button>

        <button class="pswp__button pswp__button--arrow--right" title="Next (arrow right)">
        </button>

        <div class="pswp__caption">
          <div class="pswp__caption__center"></div>
        </div>

      </div>

    </div>

  </div>
</template>

<script>
  import 'photoswipe/dist/photoswipe.css'
  import 'photoswipe/dist/default-skin/default-skin.css'
  import PhotoSwipe from 'photoswipe/dist/photoswipe'
  import UI from 'photoswipe/dist/photoswipe-ui-default'

  export default {
    methods: {
      open (index, list, params = {
        captionEl: false,
        fullscreenEl: false,
        history: false,
        shareEl: false,
        tapToClose: true
      }) {
        let photoswipe = new PhotoSwipe(this.$el, UI, list, this.options(index, params))

        // 如果未设置显示宽度和高度，则用图片自己的宽度和高度
        photoswipe.listen('gettingData', (index, item) => {
          if (item.w < 1 || item.h < 1) {
            let img = new Image()
            img.src = item.src
            img.onload = function () {
              item.w = this.width
              item.h = this.height
              photoswipe.invalidateCurrItems()
              photoswipe.updateSize(true)
            }
          }
        })

        photoswipe.init()

        this.photoswipe = photoswipe
      },

      /**
       * 关闭
       */
      close () {
        this.photoswipe.close()
      },

      /**
       * 合并选项
       * @param index
       * @param params
       * @returns {{x: number, y: number, w: number}}
       */
      options (index, params) {
        let options = Object.assign({
          index: index,
          getThumbBoundsFn (index) {
            /*
             let thumbnail = document.querySelectorAll('.preview-img')[index]
             let pageYScroll = window.pageYOffset || document.documentElement.scrollTop
             let rect = thumbnail.getBoundingClientRect()
             return {x: rect.left, y: rect.top + pageYScroll, w: rect.width}
             */
            return {x: 100, y: 100, w: 0}
          }
        }, params)
        return options
      }
    }
  }
</script>
