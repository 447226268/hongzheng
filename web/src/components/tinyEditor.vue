<template>
  <div class="editor">
    <editor :init="init" ref="editor" v-model="value" :tinymce-script-src="tinymceScriptSrc" />
  </div>
</template>

<script>
  import Editor from '@tinymce/tinymce-vue'

  export default {
    components: {
      Editor
    },
    name: 'tinyEditor',
    props: {
      str: {
        type: String,
        default: '',
      },
      baseUrl: {
        type: String,
        default: process.env.BASE_URL
      }
    },
    watch: {
      str: {
        handler(newVal) {
          this.value = newVal
        },
        deep: true
      }
    },
    data() {
      return {
        value: '',
        visible: false,
        tinymceScriptSrc: 'tinymce/tinymce.min.js',
        init: {
          height: 600,
          menubar: true,
          resize: false, // 调整编辑器大小工具 true（仅允许改变高度）, false（完全不让你动）, 'both'（宽高都能改变，注意引号）
          statusbar: true, // 显示隐藏状态栏 状态栏指的是编辑器最底下、左侧显示dom信息、右侧显示Tiny版权链接和调整大小的那一条。默认是显示的，设为false可将其隐藏。
          branding: false, // 隐藏右下角技术支持
          toolbar: 'undo redo | fontselect fontsizeselect lineheight |forecolor backcolor bold italic underline strikethrough link anchor|alignleft aligncenter alignright alignjustify outdent indent|bullist numlist|blockquote subscript superscript removeformat|table image media charmap emoticons hr pagebreak  preview|',
          language: 'zh_CN',
          language_url: 'tinymce/langs/zh_CN.js',
          theme: 'silver',
          theme_url: 'tinymce/themes/silver/theme.min.js',
          base_url: 'tinymce',
          suffix: '.min',
          fontsize_formats: '12px 14px 16px 18px 24px 36px 48px 56px 72px',
          plugins: 'preview searchreplace autolink directionality visualblocks visualchars fullscreen image link media template code codesample table charmap hr pagebreak nonbreaking anchor advlist lists wordcount imagetools textpattern help emoticons autosave bdmap indent2em lineheight formatpainter axupimgs',
          autosave_ask_before_unload: false,
          selector: '#textarea1',
          images_upload_handler: function (blobInfo, success, failure, progress) {
            console.log(failure, progress)
            const img = 'data:image/jpeg;base64,' + blobInfo.base64()
            success(img)
          }
        }
      }
    },
    mounted() {
      this.value = this.str
    },
    methods: {

    }
  }
</script>