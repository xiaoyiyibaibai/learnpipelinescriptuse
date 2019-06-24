module.exports = {
  base: '/',
  title: '工程效率平台',
  description: 'Just playing around',
  markdown:{
  	lineNumbers:true
  },
  themeConfig: {
        nav: [
            { text: '主页', link: '/' },
            { text: '快速上手', link: '/guid.md' },
            { text: '博文',
                items: [
                    { text: 'Java', link: '/java/' },
                    { text: 'vue', link: '/vue/' },
                    { text: 'Web', link: '/web/' }
                ]
            },
            { text: '关于', link: '/about/' },
            { text: 'Github', link: 'https://www.github.com/codeteenager' },
        ],
        sidebar: {
            '/java/': [
                "",
                "java1",
                "java2",
                "java3",
                "java4",
              ],
            "/vue/": [
                "",
                "vue1",
                "vue2",
                "vue3",
              ],
          "/web/": [
            "",
            "web1",
            "web2",
            "web3",
            ],
      },
      sidebarDepth: 2,
          lastUpdated: 'Last Updated',
      },
}