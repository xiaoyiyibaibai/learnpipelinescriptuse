module.exports = {
  base: '/',
  title: '工程效率平台',
  description: 'Just playing around',
  markdown:{
  	lineNumbers:true  // 代码块显示行号
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
            { text: '关于', link: '/about.md' },
            { text: '参考文档', link: 'https://segmentfault.com/a/1190000016333850'}, // 外部链接
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
          sidebarDepth: 2,  // e'b将同时提取markdown中h2 和 h3 标题，显示在侧边栏上
          lastUpdated: 'Last Updated',   // 文档更新时间：每个文件git最后提交的时间
      },
}